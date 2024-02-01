package edu.umb.cs681.HW01.fs.util;

import edu.umb.cs681.HW01.fs.Directory;
import edu.umb.cs681.HW01.fs.FSVisitor;
import edu.umb.cs681.HW01.fs.File;
import edu.umb.cs681.HW01.fs.Link;

import java.util.stream.Stream;

public class FileCrawlingVisitor implements FSVisitor {

    private Stream<File> files = Stream.empty(); // Start with an empty stream

    @Override
    public void visit(Link link) {

    }

    @Override
    public void visit(Directory directory) {

    }

    public void visit(File file) {
        files = Stream.concat(files, Stream.of(file));
    }

    public Stream<File> files() {
        return files;
    }
}
