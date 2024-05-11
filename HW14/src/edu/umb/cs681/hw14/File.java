package edu.umb.cs681.hw14;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class File extends FSElement {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public File(Directory parent, String name, int size, LocalDateTime CreationTime) {
        super(parent, name, size, CreationTime);
    }

    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isLink() {
        return false;
    }

    @Override
    public void accept(FSVisitor v) {
        lock.readLock().lock();
        try {
            v.visit(this);
        } finally {
            lock.readLock().unlock();
        }
    }

}