package ru.otus.patterns.composite.model;

import java.util.Date;

public class File implements FileSystemItem {
    private String name;
    private final Date created;
    private final long size; // in bytes

    public File(String name, long size) {
        this.name = name;
        this.created = new Date();
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void rename(String newName) {
        this.name = newName;
    }

    @Override
    public long getSize() {
        return size;
    }
}
