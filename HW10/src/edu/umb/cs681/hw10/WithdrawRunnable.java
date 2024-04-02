package edu.umb.cs681.hw10;

class WithdrawRunnable implements Runnable {
	private BankAccount account;

	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}

	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				account.withdraw(500);
				Thread.sleep(2000);
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}