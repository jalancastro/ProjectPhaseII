public class Nurse {
    private MessageHistory messages;

    public Nurse() {
        messages = new MessageHistory();
    }

    public static void setPatientAllergies(NurseForm form, PatientRecord record) {
        record.setAllergies(form.getPatientAllergies());
    }

    public static void setPatientHealthConcerns(NurseForm form, PatientRecord record) {
        record.setHealthConcerns(form.getPatientHealthConcerns());
    }
}
