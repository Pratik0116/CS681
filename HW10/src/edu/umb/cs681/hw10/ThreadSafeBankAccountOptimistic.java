package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class ThreadSafeBankAccountOptimistic extends ThreadSafeBankAccount {
    private double balance = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void deposit(double amount) {
        lock.writeLock().lock();
        try {
            balance += amount;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void withdraw(double amount) {
        lock.writeLock().lock();
        try {
            balance -= amount;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public double getBalance() {
        lock.readLock().lock();
        try {
            return balance;
        } finally {
            lock.readLock().unlock();
        }
    }
}
