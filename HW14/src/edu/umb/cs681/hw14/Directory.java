package edu.umb.cs681.hw14;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Directory extends FSElement {

    private LinkedList<FSElement> children = new LinkedList<FSElement>();
    private LinkedList<File> files = new LinkedList<File>();
    private LinkedList<Directory> subDirectory = new LinkedList<Directory>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
    }

    public LinkedList<FSElement> getChildren() {
        lock.readLock().lock();
        try {
            return this.children;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void appendChild(FSElement child) {
        lock.writeLock().lock();
        try {
            this.children.add(child);
            child.setParent(this);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int countChildren() {
        lock.readLock().lock();
        try {
            return this.children.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        lock.readLock().lock();
        try {
            for (FSElement element : children) {
                if (element.isDirectory()) {
                    subDirectory.add((Directory) element);
                }
            }
            return subDirectory;
        } finally {
            lock.readLock().unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.readLock().lock();
        try {
            for (FSElement element : children) {
                if (element.isFile()) {
                    files.add((File) element);
                }
            }
            return files;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getTotalSize() {
        lock.readLock().lock();
        try {
            int totalSize = 0;
            for (FSElement element : children) {
                if (element.isDirectory()) {
                    totalSize += ((Directory) element).getTotalSize();
                } else {
                    totalSize += element.getSize();
                }
            }
            return totalSize;
        } finally {
            lock.readLock().unlock();
        }
    }


    public boolean isDirectory() {
        return true;
    }

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
            for (FSElement e : children) {
                e.accept(v);
            }
        } finally {
            lock.readLock().unlock();
        }
    }


}