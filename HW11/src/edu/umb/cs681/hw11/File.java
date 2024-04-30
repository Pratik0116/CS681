package edu.umb.cs681.hw11;

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

}