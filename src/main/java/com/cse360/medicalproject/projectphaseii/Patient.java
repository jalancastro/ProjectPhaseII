package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;

public class Patient extends Application {
    private String patientId;
    private String firstName = "";
    private String lastName = "";
    private String dob = "";
    private String add = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String email = "";
    private String phone = "";
    private String allergies = "";
    private String hc = "";
    private DataAccessObject dao;
    private ComboBox<String> doctorIdComboBox;
    private TextArea messageHistoryArea;
    private TextArea messageSendArea;

    public Patient (){}

    public Patient(String id, File patientFile) {
        patientId = id;
        this.dao = new DataAccessObject("data");
        try (BufferedReader br = new BufferedReader(new FileReader(patientFile))) {
            String line;
            if ((line = br.readLine()) != null) {
                // Splitting the file line using commas
                String[] parts = line.split(",");

                // Taking in patient information from the first line
                if (parts.length >= 9) { // Ensuring at least 9 items are present
                    this.firstName = parts[0];
                    this.lastName = parts[1];
                    this.dob = parts[2];
                    this.add = parts[3];
                    this.city = parts[4];
                    this.state = parts[5];
                    this.zip = parts[6];
                    this.email = parts[7];
                    this.phone = parts[8];
                    if (parts.length > 9){
                        this.allergies = parts[9];
                    }
                    if (parts.length > 10){
                        this.hc = parts[10];
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) {
        // Setting up the main window
        primaryStage.setTitle("Patient Page");

        // Same button style and font
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);

        // Creating the main layout pane and tab pane
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();

        // Messages, Visit, and Patient tabs
        Tab messages = createMessagesTab();
        messages.setClosable(false);
        Tab visit = createVisitsTab();
        visit.setClosable(false);
        Tab patientRecord = createPatientRecordTab();
        patientRecord.setClosable(false);

        tabPane.getTabs().addAll(messages, visit, patientRecord);

        // Welcome text
        Text patientWelcomeText = new Text("Welcome, " + firstName + lastName);

        // Text formatting and placement
        patientWelcomeText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        patientWelcomeText.setX(100);
        patientWelcomeText.setY(100);

        // Adding the TabPane to the center of the BorderPane
        borderPane.setTop(patientWelcomeText);
        borderPane.setCenter(tabPane);
        borderPane.setMaxSize(600, 400);

        // Go back button
        Button goBackButton = new Button("Home");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(buttonFont);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createMessagesTab() {
        Tab messagesTab = new Tab("Messages");
        messagesTab.setClosable(false);
        VBox messagesVBox = new VBox(10);
        messagesVBox.setPadding(new Insets(10));


        Label Send = new Label("Send Message");
        Button intakeButton = new Button("Send");


        // ComboBox for selecting a doctor
        doctorIdComboBox = new ComboBox<>();
        doctorIdComboBox.setPromptText("Select a Doctor");
        doctorIdComboBox.getItems().addAll(dao.getAllDoctorIds());
        doctorIdComboBox.setOnAction(event -> displayMessagesForSelectedDoctor());

        // Text area for sending messages
        messageSendArea = new TextArea();
        messageSendArea.setEditable(true);

        // TextArea for displaying messages
        messageHistoryArea = new TextArea();
        messageHistoryArea.setEditable(false);

        // Add components to the layout
        messagesVBox.getChildren().addAll(new Label("Patient Messages:"), doctorIdComboBox, messageSendArea, new Label("Doctor Messages:"), messageHistoryArea, Send, intakeButton);
        messagesTab.setContent(new ScrollPane(messagesVBox));
        intakeButton.setOnAction(event -> sendMessageToDoctor());

        return messagesTab;
    }

    private Tab createVisitsTab() {
        Tab visitsTab = new Tab("Visits");
        visitsTab.setClosable(false);

        VBox visitsVBox = new VBox(10);
        visitsVBox.setPadding(new Insets(10));

        // Reading visit details from the text document
        List<String> visitDetailsList = readVisitDetailsFromFile(patientId + ".txt");

        // Populating visit details
        for (String visitDetails : visitDetailsList) {
            String[] parts = visitDetails.split(",");
            if (parts.length >= 4) { // Ensuring all necessary details are present
                String date = parts[0].trim();
                String doctor = parts[1].trim();
                String reason = parts[2].trim();
                String notes = parts[3].trim();

                // Creating components to display visit details
                Label dateLabel = new Label("Date: " + date);
                Label doctorLabel = new Label("Doctor: " + doctor);
                Label reasonLabel = new Label("Reason: " + reason);
                TextArea notesArea = new TextArea("Notes: " + notes);
                notesArea.setEditable(false);

                // Adding components to layout
                VBox visitDetailsVBox = new VBox(5, dateLabel, doctorLabel, reasonLabel, notesArea);
                visitDetailsVBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px;");
                visitsVBox.getChildren().add(visitDetailsVBox);
            }
        }

        // Scrollable container for visits
        ScrollPane scrollPane = new ScrollPane(visitsVBox);
        visitsTab.setContent(scrollPane);

        return visitsTab;
    }

    private Tab createPatientRecordTab() {
        Tab patientRecordTab = new Tab("Profile");
        patientRecordTab.setClosable(false);

        Text patientInfo = new Text("First Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nDate of Birth: " + dob +
                "\nAddress: " + add +
                "\nCity: " + city +
                "\nState: " + state +
                "\nZip Code: " + zip +
                "\nEmail Address: " + email +
                "\nPhone Number: " + phone +
                "\nAllergies: " + allergies +
                "\nHealth Concerns: " + hc
        );

        // Set text wrapping
        patientInfo.wrappingWidthProperty().set(400);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(patientInfo);
        scrollPane.setFitToHeight(true);

        patientRecordTab.setContent(scrollPane);

        return patientRecordTab;
    }

    private void sendMessageToDoctor() {
        String selectedDoctorId = doctorIdComboBox.getValue();
        if (selectedDoctorId != null && !selectedDoctorId.trim().isEmpty()) {
            dao.sendMessagePatient(this.patientId, selectedDoctorId, messageSendArea.getText());
            messageSendArea.clear();
            System.out.println("sent");
        }
    }

    private void displayMessagesForSelectedDoctor() {
        String selectedDoctorId = doctorIdComboBox.getValue();
        if (selectedDoctorId != null && !selectedDoctorId.trim().isEmpty()) {
            List<String> messages = dao.getMessagesForPatient(selectedDoctorId, patientId);
            messageHistoryArea.clear();
            for (String message : messages) {
                messageHistoryArea.appendText(message + "\n");
            }
        }
    }
    private List<String> readVisitDetailsFromFile(String fileName) {
        List<String> visitDetailsList = new ArrayList<>();
        File file = dao.getFile(fileName);
        if (!file.exists()) {
            return visitDetailsList;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLineSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip the first line
                }
                String[] parts = line.split(",", -1); // Split with no limit
                if (parts.length >= 4) { // Ensure all necessary details are present
                    String date = parts[0].trim();
                    String doctor = parts[1].trim();
                    String reason = parts[2].trim();
                    String notes = parts[3].trim();
                    visitDetailsList.add(date + "," + doctor + "," + reason + "," + notes);
                } else {
                    System.err.println("Invalid visit details format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return visitDetailsList;
    }
}
