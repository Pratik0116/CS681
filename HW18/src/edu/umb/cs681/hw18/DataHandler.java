package edu.umb.cs681.hw18;

import java.util.concurrent.atomic.AtomicBoolean;

class DataHandler implements Runnable {
    private final StockQuoteObservable stockObservable;
    private final AtomicBoolean running;

    public DataHandler(StockQuoteObservable stockObservable, AtomicBoolean running) {
        this.stockObservable = stockObservable;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get() && !Thread.currentThread().isInterrupted()) {
            stockObservable.addObserver(new TableObserver());
            stockObservable.addObserver(new LineChartObserver());
            stockObservable.addObserver(new ThreeDObserver());

            System.out.println(Thread.currentThread().getName() + " added observers.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
