package ru.otus.publishersubscriber.listeners;

import ru.otus.publishersubscriber.Event;

public interface EventListener {
    void accept(Event event);
}
