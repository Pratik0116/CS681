package edu.umb.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;

class Taxi implements Runnable {
    private String location;
    private String dest;
    private Dispatcher dispatcher;
    private ReentrantLock lockT = new ReentrantLock();

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getLocation() {
        lockT.lock();
        try {
            return location;
        } finally {
            lockT.unlock();
        }
    }

    public void setLocation(String loc) {
        lockT.lock();
        try {
            location = loc;
            if (!location.equals(dest)) {
                return;
            }
        } finally {
            lockT.unlock();
        }

        dispatcher.notifyAvailable(this); // Notify dispatcher
    }

    public void run(){

    }
}