package edu.umb.cs681.hw13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class SafeMessageService {
    private List<String> messages;
    private ReentrantLock lock;

    public SafeMessageService() {
        this.messages = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public void sendMessage(String message) {
        lock.lock();
        try {
            messages.add(message);
        } finally {
            lock.unlock();
        }
    }

    public List<String> getMessages() {
        lock.lock();
        try {
            return new ArrayList<>(messages);
        } finally {
            lock.unlock();
        }
    }
}
