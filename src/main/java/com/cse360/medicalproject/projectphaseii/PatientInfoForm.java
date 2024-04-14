package com.cse360.medicalproject.projectphaseii;
public class PatientInfoForm {
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


    public PatientInfoForm(String firstName, String lastName, String month, String day, String year, String address,
			String city, String state, String zipcode, String email, String phoneNumber) {
    	
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = month + "/" + day + "/" + year;	// mm/dd/yyyy
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.allergies = "";
		this.healthConcerns = "";
	}

	public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

	public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    
    
    protected String getAllergies() {
		return allergies;
	}

	protected void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	protected String getHealthConcerns() {
		return healthConcerns;
	}

	protected void setHealthConcerns(String healthConcerns) {
		this.healthConcerns = healthConcerns;
	}

	public String toString() {
		return firstName + "," + lastName + ","  + dob + ","
				+ address + "," + city + "," + state + ","
				+ zipcode + "," + email + "," + phoneNumber;
    }
    
}
