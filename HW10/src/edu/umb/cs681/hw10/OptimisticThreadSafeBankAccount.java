package edu.umb.cs681.hw10;

class OptimisticThreadSafeBankAccount extends ThreadSafeBankAccount {

    public void deposit(double amount) {
        double balance = getBalance();
        while (true) {
            if (compareAndSet(balance, balance + amount)) {
                break;
            }
            balance = getBalance();
        }
    }

    public void withdraw(double amount) {
        double balance = getBalance();
        while (true) {
            if (compareAndSet(balance, balance - amount)) {
                break;
            }
            balance = getBalance();
        }
    }

    private boolean compareAndSet(double expected, double update) {
        synchronized (this) {
            if (expected == getBalance()) {
                this.balance = update;
                return true;
            }
            return false;
        }
    }
}