package edu.umb.cs681.hw17;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        FileSystem filesystem1 = FileSystem.getFileSystem();
        FileSystem filesystem2 = FileSystem.getFileSystem();
        FileSystem filesystem3 = FileSystem.getFileSystem();

        Directory root1 = new Directory(null, "root1", 0, LocalDateTime.now());
        Directory directory1 = new Directory(root1, "directory1", 0, LocalDateTime.now());
        File file1 = new File(directory1, "file1", 100, LocalDateTime.now());
        File file2 = new File(directory1, "file2", 200, LocalDateTime.now());
        Directory directory2 = new Directory(root1, "directory2", 0, LocalDateTime.now());
        File file3 = new File(directory2, "file3", 150, LocalDateTime.now());

        Directory root2 = new Directory(null, "root2", 0, LocalDateTime.now());
        Directory directory3 = new Directory(root2, "directory3", 0, LocalDateTime.now());
        Directory directory4 = new Directory(root2, "directory4", 0, LocalDateTime.now());
        File file4 = new File(directory4, "file4", 180, LocalDateTime.now());
        File file5 = new File(directory4, "file5", 220, LocalDateTime.now());
        Directory directory5 = new Directory(directory3, "directory5", 0, LocalDateTime.now());
        File file6 = new File(directory5, "file6", 120, LocalDateTime.now());

        Directory root3 = new Directory(null, "root3", 0, LocalDateTime.now());
        Directory directory6 = new Directory(root3, "directory6", 0, LocalDateTime.now());
        Directory directory7 = new Directory(root3, "directory7", 0, LocalDateTime.now());
        Directory directory8 = new Directory(directory6, "directory8", 0, LocalDateTime.now());
        File file7 = new File(directory8, "file7", 130, LocalDateTime.now());
        File file8 = new File(directory8, "file8", 240, LocalDateTime.now());
        File file9 = new File(directory8, "file9", 190, LocalDateTime.now());

        Queue<File> identifiedFiles = new ConcurrentLinkedQueue<>();

        Lock lock = new ReentrantLock();

        AtomicBoolean terminateFlag = new AtomicBoolean(false);

        Thread thread1 = new Thread(() -> {
            FileCrawlingVisitor visitor1 = new FileCrawlingVisitor();
            root1.accept(visitor1);
            lock.lock();
            try {
                identifiedFiles.addAll(visitor1.getFiles());
            } finally {
                lock.unlock();
            }
            System.out.println("Thread 1 is terminated.");
        });

        Thread thread2 = new Thread(() -> {
            FileCrawlingVisitor visitor2 = new FileCrawlingVisitor();
            root2.accept(visitor2);
            lock.lock();
            try {
                identifiedFiles.addAll(visitor2.getFiles());
            } finally {
                lock.unlock();
            }
            System.out.println("Thread 2 is terminated.");
        });

        Thread thread3 = new Thread(() -> {
            FileCrawlingVisitor visitor3 = new FileCrawlingVisitor();
            root3.accept(visitor3);
            lock.lock();
            try {
                identifiedFiles.addAll(visitor3.getFiles());
            } finally {
                lock.unlock();
            }
            System.out.println("Thread 3 is terminated.");
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        terminateFlag.set(true);

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        try {
            for (File file : identifiedFiles) {
                System.out.println("Identified file is: " + file.getName());
            }
        } finally {
            lock.unlock();
        }
    }
}
