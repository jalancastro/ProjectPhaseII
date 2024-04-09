package com.cse360.medicalproject.projectphaseii;
import java.util.Vector;

public class MessageHistory {
    private Vector<Message> messages;

    public MessageHistory() {
        messages = new Vector<Message>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Vector<Message> getMessages() {
        return messages;
    }
}
