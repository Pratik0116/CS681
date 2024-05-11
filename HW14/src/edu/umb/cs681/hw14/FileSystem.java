package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static AtomicReference<FileSystem> fileSystem = new AtomicReference<>(null);
    private static final Lock lock = new ReentrantLock();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        lock.lock();
        try{
            if (fileSystem.get() == null)
                fileSystem.set(new FileSystem());
            return fileSystem.get();
        }finally {
            lock.unlock();
        }
    }
}