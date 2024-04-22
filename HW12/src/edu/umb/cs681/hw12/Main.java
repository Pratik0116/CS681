package edu.umb.cs681.hw12;

public class Main {
    public static void main(String[] args) {
        Aircraft aircraft = new Aircraft(new Position(40.7128, -74.0060, 10.0)); // Initial position

        // Create and start multiple threads with different position parameters
        Thread thread1 = new Thread(new AircraftRunnable(aircraft, 42.3601, -71.0589, 20.0));
        Thread thread2 = new Thread(new AircraftRunnable(aircraft, 34.0522, -118.2437, 15.0));
        Thread thread3 = new Thread(new AircraftRunnable(aircraft, 37.7749, -122.4194, 18.0));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}