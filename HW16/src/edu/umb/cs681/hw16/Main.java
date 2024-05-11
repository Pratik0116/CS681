package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();
        List<Taxi> taxis = new ArrayList<>();
        List<Thread> taxiThreads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Taxi taxi = new Taxi(dispatcher);
            taxis.add(taxi);
            Thread taxiThread = new Thread(taxi);
            taxiThread.start();
            taxiThreads.add(taxiThread);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Terminate all taxi threads
        for (Taxi taxi : taxis) {
            taxi.terminate();
        }

        for (Thread taxiThread : taxiThreads) {
            try {
                taxiThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        dispatcher.displayAvailableTaxis();
    }
}
