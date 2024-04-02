package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();

        AtomicBoolean done = new AtomicBoolean(false);
        for (int i = 0; i < 3; i++) {
            new Thread(new ReadRunnable(bankAccount, done)).start();
        }

        new Thread(new DepositRunnable(bankAccount)).start();
        new Thread(new WithdrawRunnable(bankAccount)).start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        done.set(true);
    }
}
