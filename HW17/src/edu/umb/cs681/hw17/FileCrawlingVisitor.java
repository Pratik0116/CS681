package edu.umb.cs681.hw17;

import java.util.concurrent.ConcurrentLinkedQueue;

public class FileCrawlingVisitor implements FSVisitor {
    private ConcurrentLinkedQueue<File> files = new ConcurrentLinkedQueue<>();

    public void visit(Link link) {
        return;
    }

    public void visit(Directory dir) {
        return;
    }

    public void visit(File file) {
        files.add(file);
    }

    public ConcurrentLinkedQueue<File> getFiles() {
        return files;
    }
}
