package edu.umb.cs681.hw18;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class StockQuoteObservable extends Observable<StockEvent> {
    private final Map<String, Double> quotes = new ConcurrentHashMap<>();

    public void changeQuote(String ticker, double quote) {
        quotes.put(ticker, quote);
        notifyObservers(new StockEvent(ticker, quote));
    }
}
