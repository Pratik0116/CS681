package edu.umb.cs681.hw06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancelablePrimeFactorizer extends RunnablePrimeFactorizer {
    private boolean done = false;
    private final Lock lock = new ReentrantLock();

    public RunnableCancelablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
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
            long divisor = from;
            while (dividend != 1 && divisor <= to && !done) {
                if (divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
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
        // Factorization of 36 with thread termination
        System.out.println("Factorization of 36 with thread termination:");
        RunnableCancelablePrimeFactorizer runnable = new RunnableCancelablePrimeFactorizer(36, 2, (long) Math.sqrt(36));
        Thread thread = new Thread(runnable);
        thread.start();
        runnable.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
    }
}

