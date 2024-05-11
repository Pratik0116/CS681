package edu.umb.cs681.hw15;

public class WithdrawRunnable implements Runnable {
    private ThreadSafeBankAccount2 bankAccount;

    public WithdrawRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                bankAccount.withdraw(100);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getId() + " (withdraw): Interrupted");
        } finally {
            System.out.println(Thread.currentThread().getId() + " (withdraw): Terminating");
        }
    }
}