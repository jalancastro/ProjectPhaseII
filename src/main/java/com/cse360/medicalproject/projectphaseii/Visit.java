package com.cse360.medicalproject.projectphaseii;
public class Visit {
    private String visitDate;
    private Doctor visitingDoctor;
    private String visitSummary;

    public Visit(String date, Doctor visitProv, String summary) {
        visitDate = date;
        visitingDoctor = visitProv;
        visitSummary = summary;
    }

    public void setVisitDate(String date) {
        visitDate = date;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitSummary(String visitSum) {
        visitSummary = visitSum;
    }

    public String getVisitSummary() {
        return visitSummary;
    }

    public void setHealthcareProvider(Doctor d) {
        visitingDoctor = d;
    }

    public Doctor getHealthcareProvider() {
        return visitingDoctor;
    }
}
