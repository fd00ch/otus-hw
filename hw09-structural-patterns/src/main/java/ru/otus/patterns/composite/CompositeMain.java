package ru.otus.patterns.composite;

import ru.otus.patterns.composite.model.File;
import ru.otus.patterns.composite.model.FileSystem;
import ru.otus.patterns.composite.model.FileSystemItem;
import ru.otus.patterns.composite.model.Folder;

public class CompositeMain {
    public static void main(String[] args) {
        FileSystemItem root = new Folder("/");
        FileSystemItem bin = new Folder("bin");
        FileSystemItem etc = new Folder("etc");
        FileSystemItem home = new Folder("home");
        FileSystemItem lib = new Folder("lib");
        FileSystemItem tmp = new Folder("tmp");
        FileSystemItem usr = new Folder("usr");
        FileSystemItem var = new Folder("var");

        FileSystemItem homeUser1 = new Folder("user1");
        FileSystemItem homeUser2 = new Folder("user2");
        FileSystemItem homeUser1File1 = new File("user1file1.txt", 100);
        FileSystemItem homeUser1File2 = new File("user1file2.txt", 200);

        FileSystemItem homeUser2File1 = new File("user1file1.txt", 300);
        FileSystemItem homeUser2File2 = new File("user1file2.txt", 400);

        root.add(bin);
        root.add(etc);
        root.add(home);
        root.add(lib);
        root.add(tmp);
        root.add(usr);
        root.add(var);

        home.add(homeUser1);
        home.add(homeUser2);

        homeUser1.add(homeUser1File1);
        homeUser1.add(homeUser1File2);

        homeUser2.add(homeUser2File1);
        homeUser2.add(homeUser2File2);

        FileSystem fileSystem = new FileSystem(root);
        System.out.println("File system total size: " + fileSystem.getRoot().getSize());
    }
}
