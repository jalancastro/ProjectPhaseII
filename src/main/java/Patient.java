import java.util.Vector;

public class Patient {
    private String patientID;
    private PatientRecord record;
    private Vector<Visit> visitHistory;

    public Patient(String ID, PatientRecord rec, Visit intialVisit) {
        patientID = ID;
        record = rec;
        visitHistory.add(intialVisit);
    }

    public void addVisit(Visit visit) {
        visitHistory.add(visit);
    }

    public Vector<Visit> getVisitHistory() {
        return visitHistory;
    }

    //Needs Implementation
    public void sortVisitHistoryByDate() {

    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPaitentID() {
        return patientID;
    }
}
