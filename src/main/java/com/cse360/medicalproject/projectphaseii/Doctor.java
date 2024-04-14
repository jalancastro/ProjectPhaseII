package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class Doctor extends Application {
    private String doctorId;
    private String doctorName;
    private DataAccessObject dao;
    private ComboBox<String> patientIdComboBox;
    private TextArea messageHistoryArea;

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
        Tab homeTab = createHomeTab();
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

    private Tab createHomeTab() {
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
        intakeButton.setOnAction(event -> startNewIntake());

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

        // ComboBox for selecting a patient
        patientIdComboBox = new ComboBox<>();
        patientIdComboBox.setPromptText("Select a Patient");
        patientIdComboBox.getItems().addAll(dao.getAllPatientIds());
        patientIdComboBox.setOnAction(event -> displayMessagesForSelectedPatient());

        // TextArea for displaying messages
        messageHistoryArea = new TextArea();
        messageHistoryArea.setEditable(false);

        // Add components to the layout
        messagesVBox.getChildren().addAll(new Label("Patient Messages:"), patientIdComboBox, messageHistoryArea);
        messagesTab.setContent(new ScrollPane(messagesVBox));

        return messagesTab;
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

    private void startNewIntake() {
        // Implementation for starting a new intake
    }

    
}
