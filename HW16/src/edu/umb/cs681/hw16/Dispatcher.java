package edu.umb.cs681.hw16;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

class Dispatcher {
    private HashSet<Taxi> availableTaxis = new HashSet<>();
    private ReentrantLock lockD = new ReentrantLock();

    public void notifyAvailable(Taxi t) {
        lockD.lock();
        try {
            availableTaxis.add(t);
        } finally {
            lockD.unlock();
        }
    }

    public void displayAvailableTaxis() {
        lockD.lock();
        try {
            for (Taxi t : availableTaxis) {
                System.out.println("Taxi Location: " + t.getLocation());
            }
        } finally {
            lockD.unlock();
        }
    }
}
