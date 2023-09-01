package ru.otus.patterns.composite.model;

import java.util.Date;

public interface FileSystemItem {
    String getName();
    Date getCreated();
    void rename(String newName);
    long getSize();

    default void add(FileSystemItem item) {
        throw new UnsupportedOperationException();
    }
    default void remove(FileSystemItem item) {
        throw new UnsupportedOperationException();
    }

}
