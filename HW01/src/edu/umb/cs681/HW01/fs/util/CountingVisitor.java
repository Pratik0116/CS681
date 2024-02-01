package edu.umb.cs681.HW01.fs.util;

import edu.umb.cs681.HW01.fs.Directory;
import edu.umb.cs681.HW01.fs.FSVisitor;
import edu.umb.cs681.HW01.fs.File;
import edu.umb.cs681.HW01.fs.Link;

public class CountingVisitor implements FSVisitor {
    private int directoryNumber = 0;
    private int fileNumber = 0;
    private int linkNumber = 0;

    public void visit(Link link) {
        linkNumber++;
    }

    public void visit(Directory dir) {
        directoryNumber++;
    }

    public void visit(File file) {
        fileNumber++;
    }

    public int getDirectoryNumber() {
        return directoryNumber;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public int getLinkNumber() {
        return linkNumber;
    }
}
