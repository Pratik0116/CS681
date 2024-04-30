package edu.umb.cs681.hw15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 implements BankAccount {
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	private boolean done = false;

	public void setDone() {
		lock.lock();
		try {
			done = true;
			sufficientFundsCondition.signalAll();
			belowUpperLimitFundsCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public boolean isDone() {
		lock.lock();
		try {
			return done;
		} finally {
			lock.unlock();
		}
	}

	public void deposit(double amount) {
		lock.lock();
		try {
			if (done) return; // Check if termination flag is set

			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() +
					" (deposit): current balance: " + balance);
			while (balance >= 300 && !done) { // Check termination flag before waiting
				System.out.println(Thread.currentThread().getId() +
						" (deposit): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			if (!done) { // Check termination flag before updating balance
				balance += amount;
				System.out.println(Thread.currentThread().getId() +
						" (deposit): new balance: " + balance);
				sufficientFundsCondition.signalAll();
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public void withdraw(double amount) {
		lock.lock();
		try {
			if (done) return; // Check if termination flag is set

			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() +
					" (withdraw): current balance: " + balance);
			while (balance <= 0 && !done) { // Check termination flag before waiting
				System.out.println(Thread.currentThread().getId() +
						" (withdraw): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			if (!done) { // Check termination flag before updating balance
				balance -= amount;
				System.out.println(Thread.currentThread().getId() +
						" (withdraw): new balance: " + balance);
				belowUpperLimitFundsCondition.signalAll();
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() {
		return this.balance;
	}
}
