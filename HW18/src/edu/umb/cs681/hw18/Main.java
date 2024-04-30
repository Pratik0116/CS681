package edu.umb.cs681.hw18;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        StockQuoteObservable stockObservable = new StockQuoteObservable();

        // Create 10+ data handler threads
        final int numThreads = 10;
        Thread[] dataHandlerThreads = new Thread[numThreads];
        AtomicBoolean running = new AtomicBoolean(true);

        for (int i = 0; i < numThreads; i++) {
            dataHandlerThreads[i] = new Thread(new DataHandler(stockObservable, running), "DataHandler-" + i);
            dataHandlerThreads[i].start();
        }

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            String ticker = "ticket1";
            double quote = 100.0 + random.nextDouble() * 10;
            stockObservable.changeQuote(ticker, quote);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        running.set(false);
        for (Thread thread : dataHandlerThreads) {
            thread.interrupt();
        }
    }
}