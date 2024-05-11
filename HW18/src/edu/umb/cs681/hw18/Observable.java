package edu.umb.cs681.hw18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Observable<T> {
    private final Lock lockObs = new ReentrantLock();
    private final List<Observer<T>> observers = new ArrayList<>();
    private boolean isTerminated = false;

    public void addObserver(Observer<T> observer) {
        lockObs.lock();
        try {
            if (!isTerminated) {
                observers.add(observer);
            }
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
        List<Observer<T>> snapshot;
        lockObs.lock();
        try {
            if (isTerminated) return;
            snapshot = new ArrayList<>(observers);
        } finally {
            lockObs.unlock();
        }

        for (Observer<T> observer : snapshot) {
            observer.update(this, event);
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

    public void clearObservers() {
        lockObs.lock();
        try {
            observers.clear();
        } finally {
            lockObs.unlock();
        }
    }

    public void terminate() {
        lockObs.lock();
        try {
            isTerminated = true;
            clearObservers();
        } finally {
            lockObs.unlock();
        }
    }

    public void join() throws InterruptedException {
        // Wait for all threads to complete
        for (Observer<T> observer : observers) {
            if (observer instanceof Thread) {
                ((Thread) observer).join();
            }
        }
    }
}
