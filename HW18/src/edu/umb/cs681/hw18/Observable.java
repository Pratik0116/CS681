package edu.umb.cs681.hw18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Observable<T> {
    private final Lock lockObs = new ReentrantLock();
    private final List<Observer<T>> observers = new ArrayList<>();

    public void addObserver(Observer<T> observer) {
        lockObs.lock();
        try {
            observers.add(observer);
        } finally {
            lockObs.unlock();
        }
    }

    public void removeObserver(Observer<T> observer) {
        lockObs.lock();
        try {
            observers.remove(observer);
        } finally {
            lockObs.unlock();
        }
    }

    public void notifyObservers(T event) {
        lockObs.lock();
        try {
            for (Observer<T> observer : observers) {
                observer.update(this, event);
            }
        } finally {
            lockObs.unlock();
        }
    }

    public int countObservers() {
        lockObs.lock();
        try {
            return observers.size();
        } finally {
            lockObs.unlock();
        }
    }

    public List<Observer<T>> getObservers() {
        lockObs.lock();
        try {
            return new ArrayList<>(observers);
        } finally {
            lockObs.unlock();
        }
    }

    public void clearObservers() {
        lockObs.lock();
        try {
            observers.clear();
        } finally {
            lockObs.unlock();
        }
    }

    public void terminateThreads() {
        Thread.currentThread().interrupt();
    }
}
