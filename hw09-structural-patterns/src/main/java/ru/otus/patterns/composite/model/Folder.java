package ru.otus.patterns.composite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Folder implements FileSystemItem {
    private String name;
    private final Date created;
    private final List<FileSystemItem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
        this.created = new Date();
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
        return children.stream()
                .reduce(0L, (size, child) -> size + child.getSize(), Long::sum);
    }

    @Override
    public void add(FileSystemItem item) {
        children.add(item);
    }

    @Override
    public void remove(FileSystemItem item) {
        children.remove(item);
    }
}
