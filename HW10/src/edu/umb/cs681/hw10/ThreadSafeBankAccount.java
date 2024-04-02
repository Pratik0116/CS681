package edu.umb.cs681.hw10  ;

import java.util.concurrent.locks.ReentrantLock;

class ThreadSafeBankAccount implements BankAccount {
    double balance = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void deposit(double amount) {
        lock.lock();
        try {
            System.out.println("Lock obtained...");
            System.out.print("Current balance (deposit)= " + balance);
            balance = balance + amount;
            System.out.println(", New balance (deposit)= " + balance);
        } finally {
            lock.unlock();
            System.out.println("Lock released...");
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            System.out.println("Lock obtained...");
            System.out.print("Current balance (withdraw)= " + balance);
            balance = balance - amount;
            System.out.println(", New balance (withdraw)= " + balance);
        } finally {
            lock.unlock();
            System.out.println("Lock released...");
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return this.balance;
        } finally {
            lock.unlock();
        }
    }
}