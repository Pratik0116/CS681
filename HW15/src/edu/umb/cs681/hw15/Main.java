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
            Thread.sleep(5000); // Let threads run for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bankAccount.setDone(); // Set termination flag

        try {
            withdrawThread1.join();
            withdrawThread2.join();
            depositThread1.join();
            depositThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads terminated.");
    }
}
