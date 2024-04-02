package edu.umb.cs681.hw11;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {
    private static AtomicReference<FileSystem> fileSystem = new AtomicReference<>(null);
    private static final Object lock = new Object();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        if (fileSystem.get() == null) {
            synchronized (lock) {
                if (fileSystem.get() == null) {
                    fileSystem.set(new FileSystem());
                }
            }
        }
        return fileSystem.get();
    }
}