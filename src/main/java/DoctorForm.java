public class DoctorForm {
    private String summary;
    private String visitDate;
    private HealthcareProvider provider;
    private MessageHistory messages;

    public DoctorForm(String summary, String visitDate, HealthcareProvider provider) {
        this.summary = summary;
        this.visitDate = visitDate;
        this.provider = provider;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public void setProvider(HealthcareProvider provider) {
        this.provider = provider;
    }

    public MessageHistory getMessages() {
        return messages;
    }
}
