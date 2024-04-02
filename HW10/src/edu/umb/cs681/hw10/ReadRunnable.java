package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicBoolean;

class ReadRunnable implements Runnable {
    private BankAccount account;
    private AtomicBoolean done;

    public ReadRunnable(BankAccount account, AtomicBoolean done) {
        this.account = account;
        this.done = done;
    }

    public void run() {
        while (!done.get()) {
            System.out.println("Balance= " + account.getBalance());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}