package ru.otus.publishersubscriber;

import ru.otus.publishersubscriber.listeners.EventListener;

import java.util.List;

public interface EventedList<T> extends List<T> {
    void addListener(EventType type, EventListener listener);
    void removeListener(EventType type, EventListener listener);
    void notifyListeners(Event event);
}
