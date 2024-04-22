package edu.umb.cs681.hw13;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceUnsafe {
    private List<String> messages;

    public MessageServiceUnsafe() {
        this.messages = new ArrayList<>();
    }

    public void sendMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
