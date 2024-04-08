public class Message {
    private String subject;
    private String recipientID;
    private String senderID;
    private String messageContent;

    public Message(String subject, String recID, String sendID, String message) {
        this.subject = subject;
        recipientID = recID;
        senderID = sendID;
        messageContent = message;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getSubject() {
        return subject;
    }
}
