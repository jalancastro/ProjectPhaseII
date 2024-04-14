package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Nurse extends Application{
    private DataAccessObject dao;
    private String currPatientID;

    public Nurse(DataAccessObject dao, String currPatientID) {
    	this.dao = dao;
    	this.currPatientID = currPatientID;
    }
    
    @Override
    public void start(Stage primaryStage) {
    	// Grab the patient's record with matching id
    	PatientRecord currPatient = dao.dataFileToRecord(currPatientID);
    	
    	GridPane intakeGrid = new GridPane();
        intakeGrid.setPadding(new Insets (10));
        // Horizontal gap between fields
        intakeGrid.setHgap(5);
        // Vertical gap between fields
        intakeGrid.setVgap(15);
        
        Scene intakeRoot = new Scene(intakeGrid , 800, 600);
        intakeRoot.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        
        Label intakeLabel = new Label("In-take Form");
        intakeLabel.getStyleClass().add("patient-form-text");
        intakeLabel.setStyle("-fx-font-size: 18px;");
        
       
        Label patientLbl = new Label("Patient:");
        patientLbl.getStyleClass().add("patient-form-text");
        
        Label nameLbl = new Label(currPatient.getFirstName() + " " + currPatient.getLastName());
        nameLbl.getStyleClass().add("patient-form-text");
                
        
        // Column 0
        GridPane.setConstraints(intakeLabel, 0, 0, 3, 1, null, null, null, null, null);
        intakeGrid.getChildren().add(intakeLabel);
        // Column 1
        GridPane.setConstraints(patientLbl, 0, 1, 3, 1, null, null, null, null, null);
        intakeGrid.getChildren().add(patientLbl);
        // Column 2
        GridPane.setConstraints(nameLbl, 0, 2, 2, 1, null, null, null, null, null);
        intakeGrid.getChildren().add(nameLbl);
        
        
        
    	
    	primaryStage.setScene(intakeRoot);
    	primaryStage.show();
    }
    
    public static void setPatientAllergies(NurseForm form, PatientRecord record) {
        record.setAllergies(form.getPatientAllergies());
    }

    public static void setPatientHealthConcerns(NurseForm form, PatientRecord record) {
        record.setHealthConcerns(form.getPatientHealthConcerns());
    }
    
   // public static 
}
