module com.cse360.medicalproject.projectphaseii {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cse360.medicalproject.projectphaseii to javafx.fxml;
    exports com.cse360.medicalproject.projectphaseii;
}