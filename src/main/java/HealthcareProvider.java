import java.util.Vector;

public class HealthcareProvider {
    protected String providerID;
    protected String firstName;
    protected String lastName;
    protected Vector<Message> messages;


    public HealthcareProvider(String ID, String fname, String lname) {
        providerID = ID;
        firstName = fname;
        lastName = lname;
    } 

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMessages(Vector<Message> messages) {
        this.messages = messages;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Vector<Message> getMessages() {
        return messages;
    }

    public String getProviderID() {
        return providerID;
    }

    

}
