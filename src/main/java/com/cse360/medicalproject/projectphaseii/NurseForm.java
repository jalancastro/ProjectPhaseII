package com.cse360.medicalproject.projectphaseii;
public class NurseForm {
    private String patientAllergies;
    private String patientHealthConcerns;

    public NurseForm(String allergies, String healthConcerns) {
        patientAllergies = allergies;
        patientHealthConcerns = healthConcerns;
    }

    public void setPatientAllergies(String patientAllergies) {
        this.patientAllergies = patientAllergies;
    }

    public void setPatientHealthConcerns(String patientHealthConcerns) {
        this.patientHealthConcerns = patientHealthConcerns;
    }

    public String getPatientAllergies() {
        return patientAllergies;
    }

    public String getPatientHealthConcerns() {
        return patientHealthConcerns;
    }
}
