package ru.otus.publishersubscriber;

import ru.otus.publishersubscriber.listeners.DeleteListener;
import ru.otus.publishersubscriber.listeners.InsertListener;

public class Test {
    public static void main(String[] args) {

        EventedList<Object> eventedList = new EventedListImpl<>();
        var insertListener = new InsertListener();
        var deleteListener = new DeleteListener();
        eventedList.addListener(EventType.ADD, insertListener);
        eventedList.addListener(EventType.DELETE, deleteListener);

        var firstItem = "firstItem";
        var secondItem = "secondItem";
        var thirdItem = "thirdItem";
        var fourthItem = "fourthItem";

        eventedList.add(firstItem);
        eventedList.add(secondItem);
        eventedList.add(thirdItem);
        eventedList.add(fourthItem);

        eventedList.remove(firstItem);
        eventedList.remove(fourthItem);
        System.out.println("Done");
    }
}
