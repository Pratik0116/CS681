package edu.umb.cs681.hw13;

public class Main {
    private static void testThreadUnsafe() {
        MessageServiceUnsafe messageServiceUnsafe = new MessageServiceUnsafe();

        // Create and start multiple threads to send messages concurrently
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    messageServiceUnsafe.sendMessage("Message from Thread " + Thread.currentThread().getId());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        // Wait for all threads to finish
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print all messages
        System.out.println("Messages (Thread-Unsafe): " + messageServiceUnsafe.getMessages());
    }

    private static void testThreadSafe() {
        SafeMessageService safeMessageService = new SafeMessageService();

        // Create and start multiple threads to send messages concurrently
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    safeMessageService.sendMessage("Message from Thread " + Thread.currentThread().getId());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        // Wait for all threads to finish
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print all messages
        System.out.println("Messages (Thread-Safe): " + safeMessageService.getMessages());
    }

    public static void main(String[] args) {
        // Testing thread-unsafe version
        System.out.println("Testing thread-unsafe version:");
        testThreadUnsafe();

        // Testing thread-safe version
        System.out.println("\nTesting thread-safe version:");
        testThreadSafe();
    }
}
