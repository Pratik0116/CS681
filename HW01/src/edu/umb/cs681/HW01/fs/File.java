package edu.umb.cs681.HW01.fs;

import java.time.LocalDateTime;

public class File extends FSElement {

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
        v.visit(this);
    }

    public String getExtension() {
        String name = this.getName();
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex != -1) ? name.substring(dotIndex + 1) : "";
    }

}