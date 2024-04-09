package com.cse360.medicalproject.projectphaseii;
import java.util.Vector;

public class HealthcareProvider {
    protected String providerID;
    protected String firstName;
    protected String lastName;
    protected MessageHistory messages;


    public HealthcareProvider(String ID, String fname, String lname) {
        providerID = ID;
        firstName = fname;
        lastName = lname;
        messages = new MessageHistory();
    } 

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public MessageHistory getMessages() {
        return messages;
    }

    public String getProviderID() {
        return providerID;
    }

    

}
