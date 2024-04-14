package com.cse360.medicalproject.projectphaseii;

import java.util.stream.Stream;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Nurse extends Application{
    private DataAccessObject dao;
    private String currPatientID;
    private IntakeForm intake;
    private Doctor providingDoctor;

    public Nurse(DataAccessObject dao, String currPatientID, Doctor providingDoctor) {
    	this.dao = dao;
    	this.currPatientID = currPatientID;
    	this.providingDoctor = providingDoctor;
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
        
        Scene intakeRoot = new Scene(intakeGrid , 600, 600);
        intakeRoot.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        
        Label intakeLabel = new Label("In-take Form");
        intakeLabel.getStyleClass().add("patient-form-text");
        intakeLabel.setStyle("-fx-font-size: 18px;");
        
       
        Label patientLbl = new Label("Patient:");
        patientLbl.getStyleClass().add("patient-form-text");
        
        Label nameLbl = new Label(currPatient.getFirstName() + " " + currPatient.getLastName());
        nameLbl.getStyleClass().add("patient-form-text");
        
        Label dobLbl = new Label(currPatient.getDob());
        
        Label vitalsLbl = new Label("Vitals");
        nameLbl.getStyleClass().add("patient-form-text");
        
        TextField weight = new TextField();
        weight.setPromptText("Weight");
        
        TextField height = new TextField();
        height.setPromptText("Height");
        
        TextField bodyTemp = new TextField();
        bodyTemp.setPromptText("Body Temperature");
        
        TextField bp = new TextField();
        bp.setPromptText("Blood Pressure");
        
        Stream.of(weight, height, bodyTemp, bp).forEach(TextField ->
        		TextField.getStyleClass().add("patient-intake-fields")
        );
        
        TextField [] vitalFields = {weight, height, bodyTemp, bp};
        
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);
        
        Button submitBtn = new Button("Confirm");
        submitBtn.setStyle(buttonStyle);
        submitBtn.setFont(buttonFont);
        submitBtn.setPrefSize(325, 75);

        // Handle confirm button
        submitBtn.setOnAction(event -> {
           	 this.intake = new IntakeForm(weight.getText(), height.getText(), bodyTemp.getText(), bp.getText());
           	 startNurseForm(primaryStage, currPatient);
           	 
        });
                
        intakeGrid.setAlignment(Pos.TOP_CENTER);
        // Column 0
        GridPane.setConstraints(intakeLabel, 0, 0, 3, 1, HPos.LEFT, null, null, null, null);
        intakeGrid.getChildren().add(intakeLabel);
        // Column 1
        GridPane.setConstraints(patientLbl, 0, 1, 3, 1, HPos.LEFT, null, null, null, null);
        intakeGrid.getChildren().add(patientLbl);
        // Column 2
        GridPane.setConstraints(nameLbl, 0, 2, 2, 1, HPos.LEFT, null, null, null, null);
        intakeGrid.getChildren().add(nameLbl);
        // Column 3
        GridPane.setConstraints(dobLbl, 0, 3, 2, 1, HPos.LEFT, null, null, null, null);
        intakeGrid.getChildren().add(dobLbl);
        // Column 4
        GridPane.setConstraints(vitalsLbl, 0, 4, 3, 1, HPos.CENTER, null, null, null, null);
        intakeGrid.getChildren().add(vitalsLbl);
        // Columns 5-8
        for(int i = 0; i < vitalFields.length; i++) {
        	GridPane.setConstraints(vitalFields[i], 0, 5+i, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
        	intakeGrid.getChildren().add(vitalFields[i]);
        }
        // Column 9
        GridPane.setConstraints(submitBtn, 0, 9, 3, 1, HPos.RIGHT, VPos.BOTTOM, null, null, null);
        intakeGrid.getChildren().add(submitBtn);
    	
    	primaryStage.setScene(intakeRoot);
    	primaryStage.show();
    }
    
    public static void setPatientAllergies(NurseForm form, PatientRecord record) {
        record.setAllergies(form.getPatientAllergies());
    }

    public static void setPatientHealthConcerns(NurseForm form, PatientRecord record) {
        record.setHealthConcerns(form.getPatientHealthConcerns());
    }
    
    private void startNurseForm(Stage primaryStage, PatientRecord currPatient) {
    	GridPane nurseFormGrid = new GridPane();
        nurseFormGrid.setPadding(new Insets (10));
        // Horizontal gap between fields
        nurseFormGrid.setHgap(5);
        // Vertical gap between fields
        nurseFormGrid.setVgap(10);
        
        Scene nurseFormRoot = new Scene(nurseFormGrid , 600, 600);
        nurseFormRoot.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        
        Label nurseFormLbl = new Label("Nurse Form");
        nurseFormLbl.getStyleClass().add("patient-form-text");
        nurseFormLbl.setStyle("-fx-font-size: 18px;");
        
        Label patientLbl = new Label("Patient:");
        Label nameLbl = new Label(currPatient.getFirstName() + " " + currPatient.getLastName()); 
        Label dobLbl = new Label(currPatient.getDob());
        
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);
        
        Label hhLbl = new Label("Health History");
        TextArea healthHistory = new TextArea();
        
        Label medLbl = new Label("Medications");
        TextArea medArea = new TextArea();
        
        Label immuneLbl = new Label("Immunizations");
        TextArea immArea = new TextArea();
        
        Label allergiesLbl = new Label("Allergies");
        TextArea allergiesArea = new TextArea();
        allergiesArea.setPromptText("Enter patient allergies");
        
        Label hcLbl = new Label("Health Concerns");
        TextArea hcArea = new TextArea();
        hcArea.setPromptText("Enter health concerns");
        
        // Style labels
        Stream.of(patientLbl, nameLbl, dobLbl, hhLbl, medLbl, immuneLbl, allergiesLbl, hcLbl).forEach(Label ->
				Label.getStyleClass().add("patient-form-text")
        		);
        
        // Style Un-editable text areas
        Stream.of(healthHistory, medArea, immArea).forEach(TextArea ->
				TextArea.setEditable(false)
        		);
        
        Button submitBtn = new Button("Submit");
        submitBtn.setStyle(buttonStyle);
        submitBtn.setFont(buttonFont);
        submitBtn.setPrefSize(325, 75);

        // Handle confirm button
        submitBtn.setOnAction(event -> {
        	currPatient.setAllergies(allergiesArea.getText());
        	currPatient.setHealthConcerns(hcArea.getText());
        	// Update patient record in data folder
        	PatientInfoForm infoToUpdate = new PatientInfoForm(currPatient);
        	Receptionist frontDesk = new Receptionist(dao, infoToUpdate, currPatientID);
        	frontDesk.createPatientFile();
        	providingDoctor.startDoctorForm(primaryStage, currPatient, currPatientID);
        	
           	 
        });
        
        Label [] formLbls = {hhLbl, medLbl, immuneLbl, allergiesLbl, hcLbl};
        TextArea [] formAreas = {healthHistory, medArea, immArea, allergiesArea, hcArea};
        
        nurseFormGrid.setAlignment(Pos.TOP_CENTER);
        // Column 0
        GridPane.setConstraints(nurseFormLbl, 0, 0, 3, 1, HPos.LEFT, null, null, null, null);
        nurseFormGrid.getChildren().add(nurseFormLbl);
        // Column 1
        GridPane.setConstraints(patientLbl, 0, 1, 3, 1, HPos.LEFT, null, null, null, null);
        nurseFormGrid.getChildren().add(patientLbl);
        // Column 2
        GridPane.setConstraints(nameLbl, 0, 2, 2, 1, HPos.LEFT, null, null, null, null);
        nurseFormGrid.getChildren().add(nameLbl);
        // Column 3
        GridPane.setConstraints(dobLbl, 0, 3, 2, 1, HPos.LEFT, null, null, null, null);
        nurseFormGrid.getChildren().add(dobLbl);
        // Add each label & its corresponding text area
        for(int i = 0; i < formLbls.length; i++) {
            GridPane.setConstraints(formLbls[i], 0, 4+(2*i), 3, 1, HPos.LEFT, null, null, null, null);
            nurseFormGrid.getChildren().add(formLbls[i]);
            GridPane.setConstraints(formAreas[i], 0, 4+(2*i + 1), 3, 1, HPos.LEFT, null, null, null, null);
            nurseFormGrid.getChildren().add(formAreas[i]);
        }
        // Add submit button
        GridPane.setConstraints(submitBtn, 0, 16, 3, 1, HPos.CENTER, VPos.BOTTOM, null, null, null);
        nurseFormGrid.getChildren().add(submitBtn);
        
        
    	primaryStage.setScene(nurseFormRoot);
    	primaryStage.show();
    	
    }
    
   // public static 
}
