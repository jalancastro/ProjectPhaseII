package com.cse360.medicalproject.projectphaseii;
public class Receptionist {

    //needs implementation
    public static void createPaitentID() {

    }

    //needs implementation
    public static void createProviderID() {

    }

    //needs changes to implemtation
    public static Patient createPatient(PatientInfoForm form) {
        Patient newPatient = new Patient(null, new PatientRecord(form), null); //Fillout ID and initalVisit with relevant information
        return newPatient;
    }

    public static HealthcareProvider createHealthcareProvider() {
        return null;
    }
}
