package edu.umb.cs681.HW06;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrimeFactorizer {
	protected long dividend, from, to;
	protected LinkedList<Long> factors = new LinkedList<Long>();
	private boolean done = false;
	private final Lock lock = new ReentrantLock();

	public PrimeFactorizer(long dividend) {
		if (dividend >= 2) {
			this.dividend = dividend;
			this.from = 2;
			this.to = dividend;
		} else {
			throw new RuntimeException("dividend must be >= 2. dividend==" + dividend);
		}
	}

	public long getDividend() {
		return dividend;
	}

	public long getFrom() {
		return from;
	}

	public long getTo() {
		return to;
	}

	public LinkedList<Long> getPrimeFactors() {
		return factors;
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void generatePrimeFactors() {
		lock.lock();
		try {
			long divisor = 2;
			while (dividend != 1 && divisor <= to && !done) {
				if (dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} else {
					if (divisor == 2) {
						divisor++;
					} else {
						divisor += 2;
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// Factorization of 36 with flag-based thread termination
		System.out.println("Factorization of 36 with flag-based thread termination:");
		PrimeFactorizer factorizer = new PrimeFactorizer(36);
		Thread thread = new Thread(() -> {
			factorizer.generatePrimeFactors();
			System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factorizer.getPrimeFactors());
		});
		thread.start();
		factorizer.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + factorizer.getPrimeFactors() + "\n");
	}
}
