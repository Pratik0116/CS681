package edu.umb.cs681.hw17;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Directory extends FSElement {

    private ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<FSElement>();
    private ConcurrentLinkedQueue<File> files = new ConcurrentLinkedQueue<File>();
    private ConcurrentLinkedQueue<Directory> subDirectory = new ConcurrentLinkedQueue<Directory>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
    }

    public ConcurrentLinkedQueue<FSElement> getChildren() {
        return this.children;
    }

    public void appendChild(FSElement child) {
        this.children.add(child);
        child.setParent(this);
    }

    public int countChildren() {
        return this.children.size();
    }

    public ConcurrentLinkedQueue<Directory> getSubDirectories() {
        for (FSElement element : children) {
            if (element.isDirectory()) {
                subDirectory.add((Directory) element);
            }
        }
        return subDirectory;
    }

    public ConcurrentLinkedQueue<File> getFiles() {
        for (FSElement element : children) {
            if (element.isFile()) {
                files.add((File) element);
            }
        }
        return files;
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement element : children) {
            if (element.isDirectory()) {
                totalSize += ((Directory) element).getTotalSize();
            } else {
                totalSize += element.getSize();
            }
        }
        return totalSize;
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
        v.visit(this);
        for (FSElement e : children) {
            e.accept(v);
        }
    }
}
