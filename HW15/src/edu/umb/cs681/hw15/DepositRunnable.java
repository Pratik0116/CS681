package edu.umb.cs681.hw15;

public class DepositRunnable implements Runnable {
    private ThreadSafeBankAccount2 bankAccount;

    public DepositRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void run() {
        while (!bankAccount.isDone()) {
            bankAccount.deposit(100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
