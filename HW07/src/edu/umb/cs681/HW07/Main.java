package edu.umb.cs681.HW07;

public class Main {
    public static void main(String[] args) {
        Runnable getInstanceTask = () -> {
            FileSystem fs = FileSystem.getFileSystem();
            System.out.println("Instance obtained: " + fs);
        };

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(getInstanceTask);
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
