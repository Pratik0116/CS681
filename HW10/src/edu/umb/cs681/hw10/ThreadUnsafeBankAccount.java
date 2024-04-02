package edu.umb.cs681.hw10;

public class ThreadUnsafeBankAccount implements BankAccount{
	private double balance = 0;

	public void deposit(double amount){
		System.out.print("Current balance (deposit)= " + balance);
		balance = balance + amount;
		System.out.println(", New balance (deposit)= " + balance);
	}
	
	public void withdraw(double amount){
		System.out.print("Current balance (withdraw)= " + balance);
		balance = balance - amount;
		System.out.println(", New balance (withdraw)= " + balance);
	}

	public double getBalance() { return this.balance; }

}
