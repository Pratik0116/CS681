package edu.umb.cs681.hw15;

public class DepositRunnable implements Runnable {
    private ThreadSafeBankAccount2 bankAccount;

    public DepositRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                bankAccount.deposit(100);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getId() + " (deposit): Interrupted");
        } finally {
            System.out.println(Thread.currentThread().getId() + " (deposit): Terminating");
        }
    }
}