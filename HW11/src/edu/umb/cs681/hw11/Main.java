package edu.umb.cs681.hw11;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = FileSystem.getFileSystem();
        Directory root = new Directory(null, "root", 0, LocalDateTime.now());
        fs.addRootDirectory(root);

        // Creating some directories and files
        Directory dir1 = new Directory(root, "directory1", 0, LocalDateTime.now());
        Directory dir2 = new Directory(root, "directory2", 0, LocalDateTime.now());
        File file1 = new File(dir1, "file1", 1, LocalDateTime.now());
        File file2 = new File(dir2, "file2", 2, LocalDateTime.now());

        AtomicBoolean terminateFlag = new AtomicBoolean(false);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Random random = new Random();
                while (!terminateFlag.get()) {
                    int randNum = random.nextInt(3);
                    switch (randNum) {
                        case 0:
                            System.out.println("Total size of root directory: " + root.getTotalSize());
                            break;
                        case 1:
                            System.out.println("Number of children in root directory: " + root.countChildren());
                            break;
                        case 2:
                            System.out.println("Sub-directories in root directory: " + root.getSubDirectories().size());
                            break;
                        default:
                            break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread terminated.");
            });
            threads[i].start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        terminateFlag.set(true);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main thread terminated.");
    }
}
