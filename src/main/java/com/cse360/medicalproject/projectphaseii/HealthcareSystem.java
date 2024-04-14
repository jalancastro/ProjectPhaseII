package com.cse360.medicalproject.projectphaseii;
import java.util.Vector;

public class HealthcareSystem {
    private Vector<Patient> patientList;
    private Vector<HealthcareProvider> providerList;
    private Vector<Receptionist> receptionistList;

    public void addPatient(Patient patient) {
        patientList.add(patient);
    }

    public void addHealthcareProvider(HealthcareProvider provider) {
        providerList.add(provider);
    }

    public void addReceptionist(Receptionist receptionist) {
        receptionistList.add(receptionist);
    }

    public Patient getPatientById(String patientID) {
        //for (Integer i = 0; i < patientList.size(); i++) {
            //if (patientID == patientList.get(i).getPatientId()) {
              //  return patientList.get(i);
            //}
        //}
        return null;
    }

    public HealthcareProvider getProviderByID(String providerID) {
        for (Integer i = 0; i < providerList.size(); i++) {
            if (providerID == providerList.get(i).getProviderID()) {
                return providerList.get(i);
            }
        }
        return null;
    }
}
