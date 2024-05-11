package edu.umb.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;

class Taxi implements Runnable {
    private String location;
    private String dest;
    private Dispatcher dispatcher;
    private boolean terminated = false;
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

        dispatcher.notifyAvailable(this);
    }

    public void terminate() {
        terminated = true;
    }

    public void run() {
        while (!terminated) {
            // Perform taxi tasks here

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " terminated.");
    }
}
