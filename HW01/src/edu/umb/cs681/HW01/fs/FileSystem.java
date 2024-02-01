package edu.umb.cs681.HW01.fs;

import edu.umb.cs681.HW01.fs.util.FileCrawlingVisitor;

import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class FileSystem {
    private LinkedList<Directory> rootDirectories = new LinkedList<Directory>();


    private static FileSystem fileSystem = null;

    public static FileSystem getFileSystem() {
        if (fileSystem == null)
            fileSystem = new FileSystem();
        return fileSystem;
    }

    public LinkedList<Directory> getRootDirectories() {
        return rootDirectories;
    }

    public void addRootDirectory(Directory dir) {
        this.rootDirectories.add(dir);
    }

    public int countFilesAfter(LocalDateTime threshold, String extension) {
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        rootDirectories.forEach(dir -> dir.accept(visitor));
        return (int) visitor.files()
                .filter(file -> file.getCreationTime().isAfter(threshold))
                .filter(file -> file.getName().endsWith(extension))
                .count();
    }

    public Map<String, IntSummaryStatistics> groupFilesByExtensionWithStats() {
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        rootDirectories.forEach(dir -> dir.accept(visitor));
        return visitor.files()
                .collect(Collectors.groupingBy(File::getExtension,
                        Collectors.summarizingInt(File::getSize)));
    }

}