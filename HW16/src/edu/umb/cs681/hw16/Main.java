package edu.umb.cs681.hw16;

public class Main {
    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();

        // Create and start multiple taxi threads
        for (int i = 0; i < 5; i++) {
            Taxi taxi = new Taxi(dispatcher);
            Thread taxiThread = new Thread(taxi);
            taxiThread.start();
        }

        // Introduce a delay to allow taxi threads to update locations
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display available taxis
        dispatcher.displayAvailableTaxis();
    }
}