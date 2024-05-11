package edu.umb.cs681.hw17;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class FSElement {

    private String name;
    private int size;
    private LocalDateTime creationTime;
    private Directory parent;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        if (parent != null) {
            parent.appendChild(this);
        } else {
            this.parent = null;
        }
        this.setName(name);
        this.setSize(size);
        this.setCreationTime(creationTime);
    }

    public Directory getParent() {
        lock.readLock().lock();
        try {
            return this.parent;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getSize() {
        lock.readLock().lock();
        try {
            return this.size;
        } finally {
            lock.readLock().unlock();
        }
    }

    public String getName() {
        lock.readLock().lock();
        try {
            return this.name;
        } finally {
            lock.readLock().unlock();
        }
    }

    public LocalDateTime getCreationTime() {
        lock.readLock().lock();
        try {
            return creationTime;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setParent(Directory parent) {
        lock.writeLock().lock();
        try {
            this.parent = parent;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setSize(int size) {
        lock.writeLock().lock();
        try {
            if (isDirectory()) {
                this.size = 0;
            } else {
                this.size = size;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setName(String name) {
        lock.writeLock().lock();
        try {
            this.name = name;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setCreationTime(LocalDateTime creationTime) {
        lock.writeLock().lock();
        try {
            this.creationTime = creationTime;
        } finally {
            lock.writeLock().unlock();
        }
    }

    abstract public boolean isDirectory();
    abstract public boolean isFile();
    protected abstract boolean isLink();

    public abstract void accept(FSVisitor v);

}