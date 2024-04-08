import java.util.Vector;

public class MessageHistory {
    private Vector<Message> messages;

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Vector<Message> getMessages() {
        return messages;
    }
}
