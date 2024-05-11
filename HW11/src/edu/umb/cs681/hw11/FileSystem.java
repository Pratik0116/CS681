package edu.umb.cs681.hw11;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {
    private LinkedList<Directory> rootDirectories = new LinkedList<Directory>();
    private static AtomicReference<FileSystem> fileSystem = new AtomicReference<>(null);

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        if (fileSystem.get() == null) {
            FileSystem fs = new FileSystem();
            if (fileSystem.compareAndSet(null, fs)) {
                return fs;
            } else {
                return fileSystem.get();
            }
        } else {
            return fileSystem.get();
        }
    }

    public LinkedList<Directory> getRootDirectories() {
        return rootDirectories;
    }

    public void addRootDirectory(Directory dir) {
        this.rootDirectories.add(dir);
    }
}
