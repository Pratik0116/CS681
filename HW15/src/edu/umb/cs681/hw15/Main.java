package edu.umb.cs681.hw15;

public class Main {
    public static void main(String[] args) {
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();

        WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
        DepositRunnable depositRunnable = new DepositRunnable(bankAccount);

        Thread withdrawThread1 = new Thread(withdrawRunnable);
        Thread withdrawThread2 = new Thread(withdrawRunnable);

        Thread depositThread1 = new Thread(depositRunnable);
        Thread depositThread2 = new Thread(depositRunnable);

        withdrawThread1.start();
        withdrawThread2.start();
        depositThread1.start();
        depositThread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        withdrawThread1.interrupt();
        withdrawThread2.interrupt();
        depositThread1.interrupt();
        depositThread2.interrupt();

        bankAccount.setDone();
    }
}
