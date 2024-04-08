public class Visit {
    private String visitDate;
    private HealthcareProvider visitProvider;
    private String visitSummary;

    public Visit(String date, HealthcareProvider visitProv, String summary) {
        visitDate = date;
        visitProvider = visitProv;
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

    public void setHealthcareProvider(HealthcareProvider hp) {
        visitProvider = hp;
    }

    public HealthcareProvider getHealthcareProvider() {
        return visitProvider;
    }
}
