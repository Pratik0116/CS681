package edu.umb.cs681.HW01.fs.util;

import edu.umb.cs681.HW01.fs.Directory;
import edu.umb.cs681.HW01.fs.FSVisitor;
import edu.umb.cs681.HW01.fs.File;
import edu.umb.cs681.HW01.fs.Link;

import java.util.LinkedList;

public class FileSearchVisitor implements FSVisitor {

    private String fileName;
    private LinkedList<File> foundFiles;

    public FileSearchVisitor(String fileName) {
        foundFiles = new LinkedList<>();
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public LinkedList<File> getFoundFiles() {
        return foundFiles;
    }

    public void visit(Link link) {
        return;
    }

    public void visit(Directory dir) {
        return;
    }

    public void visit(File file) {
        if(file.getName().equals(fileName)) {
            foundFiles.add(file);
        }
    }
}
