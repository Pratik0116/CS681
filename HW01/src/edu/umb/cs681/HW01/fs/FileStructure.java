package edu.umb.cs681.HW01.fs;

import java.time.LocalDateTime;
import java.time.Month;

public class FileStructure {
    static FileSystem fileSystem = null;

    public static FileSystem createFS()
    {
        LocalDateTime localDateTime = LocalDateTime.of(2024, Month.FEBRUARY, 1, 05, 12);
        if (fileSystem != null)
            return fileSystem;
        FileSystem fs = FileSystem.getFileSystem();
        Directory repo = new Directory(null, "repo", 0, localDateTime);
        fs.addRootDirectory(repo);
        File readme = new File(repo,"readme.md",0,localDateTime);
        Directory src = new Directory(repo,"src", 1, localDateTime);
        File A = new File(src,"A.java", 2, localDateTime);
        File B = new File(src,"B.java", 3, localDateTime);

        Directory bin = new Directory(repo,"src", 1, localDateTime);
        File a = new File(src,"A.class", 4, localDateTime);
        File b = new File(src,"B.class", 5, localDateTime);

        Directory test = new Directory(repo,"test", 6, localDateTime);
        Directory Src = new Directory(test,"Src",7, localDateTime);
        File ATest = new File(Src,"ATest.java", 8, localDateTime);
        File BTest = new File(Src,"BTest.java", 9, localDateTime);

        Directory Bin = new Directory(test,"Bin",8, localDateTime);
        File aTest = new File(Bin,"ATest.class", 9, localDateTime);
        File bTest = new File(Bin,"BTest.class", 10, localDateTime);

        fileSystem = fs;
        return fs;
    }
}
