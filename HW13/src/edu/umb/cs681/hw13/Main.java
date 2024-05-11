package edu.umb.cs681.hw13;

public class Main {
    private static void testThreadUnsafe() {
        MessageServiceUnsafe messageServiceUnsafe = new MessageServiceUnsafe();

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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Messages (Thread-Unsafe): " + messageServiceUnsafe.getMessages());
    }

    private static void testThreadSafe() {
        SafeMessageService safeMessageService = new SafeMessageService();

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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Messages (Thread-Safe): " + safeMessageService.getMessages());
    }

    public static void main(String[] args) {
        System.out.println("Testing thread-unsafe version:");
        testThreadUnsafe();

        System.out.println("\nTesting thread-safe version:");
        testThreadSafe();
    }
}
