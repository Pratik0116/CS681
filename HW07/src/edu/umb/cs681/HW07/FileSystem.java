package edu.umb.cs681.HW07;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private LinkedList<Directory> rootDirectories = new LinkedList<Directory>();
    private static FileSystem fileSystem = null;
    private static final Lock lock = new ReentrantLock();

    public static FileSystem getFileSystem() {
        lock.lock();
        try{
            if (fileSystem == null)
                fileSystem = new FileSystem();
            return fileSystem;
        }finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirectories() {
        return rootDirectories;
    }

    public void addRootDirectory(Directory dir) {
        this.rootDirectories.add(dir);
    }
}
