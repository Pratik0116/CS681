package edu.umb.cs681.hw12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Aircraft aircraft = new Aircraft(new Position(42.3601, -71.0589, 5000.0));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            double newLat = 42.3601 + i;
            double newLong = -71.0589 + i;
            double newAlt = 5000.0 + i * 1000;
            executor.submit(() -> {
                aircraft.setPosition(newLat, newLong, newAlt);
                System.out.println(Thread.currentThread().getName() + " New Position = " + aircraft.getPosition());
            });
        }
        executor.shutdown();
    }
}
