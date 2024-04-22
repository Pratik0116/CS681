package edu.umb.cs681.hw12;


public class AircraftRunnable implements Runnable {
    private final Aircraft aircraft;
    private final double newLat;
    private final double newLong;
    private final double newAlt;

    public AircraftRunnable(Aircraft aircraft, double newLat, double newLong, double newAlt) {
        this.aircraft = aircraft;
        this.newLat = newLat;
        this.newLong = newLong;
        this.newAlt = newAlt;
    }

    public void run() {
        aircraft.setPosition(newLat, newLong, newAlt);
        System.out.println(Thread.currentThread().getName() + " New Position = " + aircraft.getPosition());
    }
}
