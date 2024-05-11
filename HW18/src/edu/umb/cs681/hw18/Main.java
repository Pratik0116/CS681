package edu.umb.cs681.hw18;

public class Main {
    public static void main(String[] args) {
        StockQuoteObservable observable = new StockQuoteObservable();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                Observer<StockEvent> lineChartObserver = new LineChartObserver();
                observable.addObserver(lineChartObserver);
                try {
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                observable.removeObserver(lineChartObserver);
            });
            thread.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                Observer<StockEvent> tableObserver = new TableObserver();
                observable.addObserver(tableObserver);
                try {
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                observable.removeObserver(tableObserver);
            });
            thread.start();
        }

        observable.changeQuote("ticket1", 150.0);
        observable.changeQuote("ticket2", 2800.0);

        // Terminate data handler threads
        observable.terminateThreads();
    }
}
