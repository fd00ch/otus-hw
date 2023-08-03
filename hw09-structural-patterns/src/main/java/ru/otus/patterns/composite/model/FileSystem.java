package ru.otus.patterns.composite.model;

public class FileSystem {
    private final FileSystemItem root;

    public FileSystem(FileSystemItem root) {
        this.root = root;
    }

    public FileSystemItem getRoot() {
        return root;
    }
}
