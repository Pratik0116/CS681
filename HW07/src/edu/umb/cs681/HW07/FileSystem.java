package edu.umb.cs681.HW07;

import java.util.LinkedList;

public class FileSystem {
    private LinkedList<Directory> rootDirectories = new LinkedList<Directory>();
    private static FileSystem fileSystem = null;
    private static final Object lock = new Object(); // Define a lock object

    public static FileSystem getFileSystem() {
        synchronized (lock) { // Use synchronized block to ensure thread safety
            if (fileSystem == null)
                fileSystem = new FileSystem();
        }
        return fileSystem;
    }

    public LinkedList<Directory> getRootDirectories() {
        return rootDirectories;
    }

    public void addRootDirectory(Directory dir) {
        this.rootDirectories.add(dir);
    }
}
