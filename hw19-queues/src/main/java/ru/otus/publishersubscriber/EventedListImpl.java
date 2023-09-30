package ru.otus.publishersubscriber;

import ru.otus.publishersubscriber.listeners.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class EventedListImpl<T> extends ArrayList<T> implements EventedList<T> {
    private final Map<EventType, List<EventListener>> listeners = new ConcurrentHashMap<>();
    private final LinkedBlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
    private final Thread notifyingThread = new Thread(() -> {
        do {
            try {
                notifyListeners(eventQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!listeners.isEmpty());
    });

    @Override
    public boolean add(T object) {
        var result = super.add(object);
        publishEvent(EventType.ADD, object);
        return result;
    }

    @Override
    public T remove(int i) {
        T removed = super.remove(i);
        publishEvent(EventType.DELETE, removed);
        return removed;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        if (removed) {
            publishEvent(EventType.DELETE, (T)o);
        }
        return removed;
    }

    private void publishEvent(EventType type, T item) {
        eventQueue.add(new Event(type, item));
    }

    @Override
    public void addListener(EventType type, EventListener listener) {
        this.listeners.computeIfAbsent(type, key -> new ArrayList<>()).add(listener);
        startNotifying();
    }

    @Override
    public void removeListener(EventType type, EventListener listener) {
        this.listeners.get(type).remove(listener);
        if (this.listeners.get(type).isEmpty()) {
            this.listeners.remove(type);
        }
    }

    @Override
    public void notifyListeners(Event event) {
        var eventType = event.type();
        if (listeners.containsKey(eventType)) {
            var eventTypeListeners = listeners.get(eventType);
            eventTypeListeners.forEach(listener -> listener.accept(event));
        }
    }

    private void startNotifying() {
        if (notifyingThread.isAlive()) return;
        notifyingThread.start();
    }
}
