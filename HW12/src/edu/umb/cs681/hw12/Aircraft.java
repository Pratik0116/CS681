package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Aircraft {

    private volatile Position position; // Shared (non-final) variable
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Aircraft(Position pos) {
        this.position = pos;
    }

    public void setPosition(double newLat, double newLong, double newAlt) {
        lock.writeLock().lock();
        try {
            this.position = this.position.change(newLat, newLong, newAlt);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Position getPosition() {
        lock.readLock().lock();
        try {
            return position;
        } finally {
            lock.readLock().unlock();
        }
    }
}