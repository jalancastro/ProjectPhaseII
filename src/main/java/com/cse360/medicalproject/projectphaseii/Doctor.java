package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class Doctor extends Application {
    private String doctorId;
    private String doctorName;
    private DataAccessObject dao;
    private ComboBox<String> patientIdComboBox;
    private TextArea messageHistoryArea;
    private TextArea messageSendArea;

    public Doctor() {
    }

    public Doctor(String doctorId, String doctorName) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.dao = new DataAccessObject("data");
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the main window
        primaryStage.setTitle("Doctor Page");

        // Create the main layout pane and tab pane
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();

        // Create and add the Home and Messages tabs
        Tab homeTab = createHomeTab(primaryStage);
        tabPane.getTabs().add(homeTab);
        
        Tab messagesTab = createMessagesTab();
        tabPane.getTabs().add(messagesTab);

        // Add the TabPane to the center of the BorderPane
        borderPane.setCenter(tabPane);

        // Set the scene and show the stage
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createHomeTab(Stage primaryStage) {
        Tab homeTab = new Tab("Home");
        homeTab.setClosable(false);
        VBox homeVBox = new VBox(10);
        homeVBox.setPadding(new Insets(10));

        // Personalized greeting at the top
        Label greetingLabel = new Label("Hello, Dr. " + doctorName);
        greetingLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        // History section
        Label historyLabel = new Label("History:");
        TextArea historyArea = new TextArea();
        historyArea.setEditable(false);  // History is for display purposes only
        historyArea.setText(dao.getDoctorHistoryById(doctorId)); // Get history from DAO

        // In-takes section
        Label intakeLabel = new Label("In-takes:");
        Button intakeButton = new Button("Start New Intake");
        intakeButton.setOnAction(event -> startNewIntake(primaryStage));

        // Add components to the layout
        homeVBox.getChildren().addAll(greetingLabel, intakeLabel, intakeButton, historyLabel, historyArea);
        homeTab.setContent(new ScrollPane(homeVBox));

        return homeTab;
    }

    private Tab createMessagesTab() {
        Tab messagesTab = new Tab("Messages");
        messagesTab.setClosable(false);
        VBox messagesVBox = new VBox(10);
        messagesVBox.setPadding(new Insets(10));
        
        
        Label Send = new Label("Send Message");
        Button intakeButton = new Button("Send");
        intakeButton.setOnAction(event -> sendMessageToPatient());

    
        // ComboBox for selecting a patient
        patientIdComboBox = new ComboBox<>();
        patientIdComboBox.setPromptText("Select a Patient");
        patientIdComboBox.getItems().addAll(dao.getAllPatientIds());
        patientIdComboBox.setOnAction(event -> displayMessagesForSelectedPatient());

        // TextArea for displaying messages
        messageHistoryArea = new TextArea();
        messageHistoryArea.setEditable(false);
        
        //Text area for sending messages
        messageSendArea = new TextArea();
        messageSendArea.setEditable(true);

        // Add components to the layout
        messagesVBox.getChildren().addAll(new Label("Patient Messages:"), patientIdComboBox, messageHistoryArea, new Label("Doctor Messages:"), messageSendArea, Send, intakeButton);
        messagesTab.setContent(new ScrollPane(messagesVBox));
        
        


        return messagesTab;
    }

    private void sendMessageToPatient() {
        String selectedPatientId = patientIdComboBox.getValue();
        if (selectedPatientId != null && !selectedPatientId.trim().isEmpty()) {
            dao.sendMessage(this.doctorId, selectedPatientId, messageSendArea.getText());
            messageSendArea.clear();
        }
	}

	private void displayMessagesForSelectedPatient() {
		  String selectedPatientId = patientIdComboBox.getValue();
	        if (selectedPatientId != null && !selectedPatientId.trim().isEmpty()) {
	            List<String> messages = dao.getMessagesForDoctor(doctorId, selectedPatientId);
	            messageHistoryArea.clear();
	            for (String message : messages) {
	                messageHistoryArea.appendText(message + "\n");
	            }
	        }
    }

    private void startNewIntake(Stage primaryStage) {
    	VBox initIntakeLayout = new VBox(20);
    	initIntakeLayout.setAlignment(Pos.CENTER);
    	Scene initIntakeScene = new Scene(initIntakeLayout, 800, 600);
    	initIntakeScene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
    	
    	Label initIntakeLbl = new Label("Enter the visiting patient's ID");
    	initIntakeLbl.getStyleClass().add("patient-form-text");
    	initIntakeLbl.setStyle("-fx-font-size: 20px;");
    	
    	TextField patientIdField = new TextField();
    	patientIdField.setPromptText("Visiting Patient ID");
    	patientIdField.getStyleClass().add("new-patient-fields");
    	patientIdField.setMaxSize(350, 75);

      // Same button style and font
         String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
         Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);
       
         // Go back button
         Button goBackButton = new Button("Go Back");
         goBackButton.setStyle(buttonStyle);
         goBackButton.setFont(buttonFont);
         goBackButton.setPrefSize(325, 75);

         // Handle Go Back Button
         goBackButton.setOnAction(event -> {
             try {
                 start(primaryStage);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         });
         
         Button confirmBtn = new Button("Confirm");
         confirmBtn.setStyle(buttonStyle);
         confirmBtn.setFont(buttonFont);
         confirmBtn.setPrefSize(325, 75);

         // Handle confirm button
         confirmBtn.setOnAction(event -> {
             if(dao.isPatientIdValid(patientIdField.getText())) {
            	 // Initialize Nurse instance & display forms
            	 Nurse intakeNurse = new Nurse(dao, patientIdField.getText(), this);
            	 intakeNurse.start(primaryStage);
             }
             else {
            	 showAlert(Alert.AlertType.ERROR, primaryStage, "Confirmation Error", "Patient with ID " + 
            			 						patientIdField.getText() + " does not exist.");
             }
         });
         
         initIntakeLayout.getChildren().addAll(initIntakeLbl, patientIdField, confirmBtn, goBackButton);
    	
    	primaryStage.setScene(initIntakeScene);
    	primaryStage.show();
        
    }

    // Method to show alerts 
    private void showAlert(Alert.AlertType alertType, Stage owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    // Called from Nurse's nurseForm method
    public void startDoctorForm(Stage primaryStage, PatientRecord currPatient, String currPatientID) {
    	GridPane doctorFormGrid = new GridPane();
        doctorFormGrid.setPadding(new Insets (10));
        // Horizontal gap between fields
        doctorFormGrid.setHgap(5);
        // Vertical gap between fields
        doctorFormGrid.setVgap(10);
        
        Scene doctorFormRoot = new Scene(doctorFormGrid , 600, 800);
        doctorFormRoot.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        
        Label doctorFormLbl = new Label("DoctorForm");
        doctorFormLbl.getStyleClass().add("patient-form-text");
        doctorFormLbl.setStyle("-fx-font-size: 18px;");
        
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
        allergiesArea.setText(currPatient.getAllergies());
        
        Label hcLbl = new Label("Health Concerns");
        TextArea hcArea = new TextArea();
        hcArea.setText(currPatient.getHealthConcerns());
        
        Label notesLbl = new Label("Notes");
        TextArea notesArea = new TextArea();
        
        // Style labels
        Stream.of(patientLbl, nameLbl, dobLbl, hhLbl, medLbl, immuneLbl, allergiesLbl, hcLbl, notesLbl).forEach(Label ->
				Label.getStyleClass().add("patient-form-text")
        		);
        
        // Style Un-editable text areas
        Stream.of(healthHistory, medArea, immArea, allergiesArea, hcArea).forEach(TextArea ->
				TextArea.setEditable(false)
        		);
        
        Button orderMedBtn = new Button("Order Medication");
        orderMedBtn.setStyle(buttonStyle);
        orderMedBtn.setFont(buttonFont);
        orderMedBtn.setPrefSize(325, 75);
        
        // Handle order meds button
        orderMedBtn.setOnAction(event -> {
        	// NEEDS UI & IMPLEMENTATION
           	 
        });
        
        Button submitBtn = new Button("Submit");
        submitBtn.setStyle(buttonStyle);
        submitBtn.setFont(buttonFont);
        submitBtn.setPrefSize(325, 75);

        // Handle submit button
        submitBtn.setOnAction(event -> {
        	// Format the current date and time
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = now.format(formatter);
        	Visit visitNew = new Visit(formattedDate, this, notesArea.getText());
        	start(primaryStage);
           	 
        });
        
        Label [] formLbls = {hhLbl, medLbl, immuneLbl, allergiesLbl, hcLbl, notesLbl};
        TextArea [] formAreas = {healthHistory, medArea, immArea, allergiesArea, hcArea, notesArea};
        
        doctorFormGrid.setAlignment(Pos.TOP_CENTER);
        // Column 0
        GridPane.setConstraints(doctorFormLbl, 0, 0, 3, 1, HPos.LEFT, null, null, null, null);
        doctorFormGrid.getChildren().add(doctorFormLbl);
        // Column 1
        GridPane.setConstraints(patientLbl, 0, 1, 3, 1, HPos.LEFT, null, null, null, null);
        doctorFormGrid.getChildren().add(patientLbl);
        // Column 2
        GridPane.setConstraints(nameLbl, 0, 2, 2, 1, HPos.LEFT, null, null, null, null);
        doctorFormGrid.getChildren().add(nameLbl);
        // Column 3
        GridPane.setConstraints(dobLbl, 0, 3, 2, 1, HPos.LEFT, null, null, null, null);
        doctorFormGrid.getChildren().add(dobLbl);
        // Add each label & its corresponding text area
        for(int i = 0; i < formLbls.length; i++) {
            GridPane.setConstraints(formLbls[i], 0, 4+(2*i), 3, 1, HPos.LEFT, null, null, null, null);
            doctorFormGrid.getChildren().add(formLbls[i]);
            GridPane.setConstraints(formAreas[i], 0, 4+(2*i + 1), 3, 1, HPos.LEFT, null, null, null, null);
            doctorFormGrid.getChildren().add(formAreas[i]);
        }
        // Column 18
        GridPane.setConstraints(orderMedBtn, 1, 18, 1, 1, HPos.LEFT, VPos.BOTTOM, null, null, null);
        doctorFormGrid.getChildren().add(orderMedBtn);
        
        GridPane.setConstraints(submitBtn, 2, 18, 2, 1, HPos.RIGHT, VPos.BOTTOM, null, null, null);
        doctorFormGrid.getChildren().add(submitBtn);
        
        
    	primaryStage.setScene(doctorFormRoot);
    	primaryStage.show();
    }
    
}
