package com.cse360.medicalproject.projectphaseii;
public class MessagingSystem {

    /*public static void sendMessage(String senderID, String recipientID, String subject, String content, HealthcareSystem hSystem) {
        Message newMessage = new Message(subject, recipientID, senderID, content);
        Patient patient = hSystem.getPatientByID(recipientID);
        if (patient == null) {
            HealthcareProvider provider = hSystem.getProviderByID(recipientID);
            provider.getMessages().addMessage(newMessage);
        }
        else {
            patient.getMessages().addMessage(newMessage);
        }
    }

    public static void sendReply(String senderID, String recipientID, String origSubject, String replyContent, HealthcareSystem hSystem) {
        String replySubj = "RE: " + origSubject;
        Message newReply = new Message(replySubj, recipientID, senderID, replyContent);
        Patient patient = hSystem.getPatientByID(recipientID);
        if (patient == null) {
            HealthcareProvider provider = hSystem.getProviderByID(recipientID);
            provider.getMessages().addMessage(newReply);
        }
        else {
            patient.getMessages().addMessage(newReply);
        }
    }*/
}
