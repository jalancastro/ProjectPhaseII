package com.cse360.medicalproject.projectphaseii;
public class PatientRecord {
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String email;
    private String phoneNumber;
    private String allergies;
    private String healthConcerns;

    public PatientRecord(String fname, String lname, String dob, String add, String cit, String state, String zip, String email, String phoneNumber, String allergies, String HC) {
        firstName = fname;
        lastName = lname;
        this.dob = dob;
        address = add;
        city = cit;
        this.state = state;
        zipcode = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.allergies = allergies;
        healthConcerns = HC;

    }

    public PatientRecord(PatientInfoForm info) {
        firstName = info.getFirstName();
        lastName = info.getLastName();
        dob = info.getDob();
        address = info.getAddress();
        city = info.getCity();
        state = info.getState();
        zipcode = info.getZipcode();
        email = info.getEmail();
        phoneNumber = info.getPhoneNumber();
        allergies = "";
        healthConcerns = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getHealthConcerns() {
        return healthConcerns;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setHealthConcerns(String healthConcerns) {
        this.healthConcerns = healthConcerns;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
