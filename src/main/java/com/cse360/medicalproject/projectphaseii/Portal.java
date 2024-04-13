package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Portal extends Application {

    private DataAccessObject dao;

    @Override
    public void init() {
        // Initialize the data access object with the relative path to the data directory
        dao = new DataAccessObject("data");
    }

    public void start(Stage primaryStage) throws Exception {

        // Title for the main scene
        Text mainSceneTitle = new Text("Welcome to Pediatric Health");
        mainSceneTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        // Creating "Sign In" title placed over the buttons
        Text signInText = new Text("Sign In");
        signInText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        
        // Same button style and font
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);

        // Creating sign in buttons
        Button newPatientButton = new Button("New Patient");
        newPatientButton.setStyle(buttonStyle);
        newPatientButton.setFont(buttonFont);
        Button existingPatientButton = new Button("Existing Patient");
        existingPatientButton.setStyle(buttonStyle);
        existingPatientButton.setFont(buttonFont);
        Button healthcareProviderButton = new Button("Healthcare Provider");
        healthcareProviderButton.setStyle(buttonStyle);
        healthcareProviderButton.setFont(buttonFont);

        // Set preferred width for buttons
        double prefWidth = 325;
        double prefHeight = 75;
        newPatientButton.setPrefSize(prefWidth, prefHeight);
        existingPatientButton.setPrefSize(prefWidth, prefHeight);
        healthcareProviderButton.setPrefSize(prefWidth, prefHeight);

        // Creating VBox layout for buttons
        VBox signInButtonsBox = new VBox(50, // spacing between buttons
                signInText, newPatientButton, existingPatientButton,
                healthcareProviderButton);

        // Positioning sign in buttons
        signInButtonsBox.setAlignment(Pos.CENTER);
        signInButtonsBox.setLayoutX(335);
        signInButtonsBox.setLayoutY(200);
        
        signInButtonsBox.setPadding(new Insets(25));
        signInButtonsBox.setStyle("-fx-border-color: black; -fx-border-width: 5px;"
                +                 " -fx-border-radius: 5px;");

        VBox alignBox = new VBox(50, mainSceneTitle, signInButtonsBox);
        alignBox.setAlignment(Pos.CENTER);
        alignBox.setLayoutX(250);
        alignBox.setLayoutY(50);

        // Setting the stage
        Group mainSceneRoot = new Group(alignBox);
        Scene mainScene = new Scene(mainSceneRoot, 1000, 700);
        primaryStage.setScene(mainScene);
        
        primaryStage.setTitle("Pediatric Health");
        primaryStage.setResizable(false); // Page can not be resized
        primaryStage.show();

        // Action when buttons are clicked
        newPatientButton.setOnAction(event -> newPatientPage(primaryStage));
        existingPatientButton.setOnAction(event -> existingPatientLogin(primaryStage));
        healthcareProviderButton.setOnAction(event -> healthcarePage(primaryStage));
    }
    
    // New patient page
    private void newPatientPage(Stage primaryStage) {
        // Gridpane layout for the Patient Page
        GridPane formLayout = new GridPane();
        formLayout.setPadding(new Insets (10));
        
        // Horizontal gap between fields
        formLayout.setHgap(5);
        // Vertical gap between fields
        formLayout.setVgap(15);
        
        // Initialize the scene & acquire style sheets in "app.css"
        Scene patientScene = new Scene(formLayout, 400, 600);
        patientScene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

        //------------------- Labels -----------------
        // Label at top of the form
        Label patFormLbl = new Label("New Patient Form");
        patFormLbl.getStyleClass().add("patient-form-text");
        patFormLbl.setStyle("-fx-underline: true; -fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );
        // Label above name fields
        Label nameLbl = new Label("Name");
        nameLbl.getStyleClass().add("patient-form-text");
        
        // Label above date of birth fields
        Label dobLbl = new Label("Date of Birth");
        dobLbl.getStyleClass().add("patient-form-text");
        
        // Label above address fields
        Label addrLbl = new Label("Address");
        addrLbl.getStyleClass().add("patient-form-text");
        
        // Label above contact information fields
        Label contactLbl = new Label("Contact Information");
        contactLbl.getStyleClass().add("patient-form-text");
        //-------------------------------------------------
        
        // ---------------- Text Fields -----------------------
        // Name fields
        var firstNameField = new ValidatingTextField(userInput->true); // any string
        firstNameField.setPromptText("First Name");
        
        var lastNameField = new ValidatingTextField(userInput->true); // any string
        lastNameField.setPromptText("Last Name");
        
        // Date of birth fields
        var monthField = new ValidatingTextField(userInput->
                (userInput.matches(("(^[1-9]$)|(^1[0-2]$)"))));     // (m) 1-12
        monthField.setPromptText("Month");

        var dayField = new ValidatingTextField(userInput->
                (userInput.matches(("[1-9]|[12]\\d|3[01]$"))));     // (d) 1-31
        dayField.setPromptText("Day");

        var yearField = new ValidatingTextField(userInput->
                (userInput.matches(("^\\d{4}$"))));                    // (yyyy)
        yearField.setPromptText("Year");

        // Address fields
        var addressField = new ValidatingTextField(userInput->true); // any string
        addressField.setPromptText("Street Address");

        var cityField = new ValidatingTextField(userInput->true); // any string
        cityField.setPromptText("City");

        var stateField = new ValidatingTextField(userInput->true); // any string
        stateField.setPromptText("State");

        var zipField = new ValidatingTextField(userInput->
                (userInput.matches("^\\d{3,10}")));            //3-10-digit integer
        zipField.setPromptText("Zip/Postal Code");

        // Initializing regex strings
        // Email regex format: {anything}@{anything}.{any 2-4 length word}
        String emailFormat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        // Phone regex format: xxx-xxx-xxxx, x-xxx-xxx-xxxx, with () variations
        String phoneFormat = "^\\s*(?:\\+?(\\d{1,3}))?"
                + "[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*"
                + "(\\d{4})(?: *x(\\d+))?\\s*$";

        // Contact fields
        var emailField = new ValidatingTextField(userInput->
                (userInput.matches(emailFormat)));
        emailField.setPromptText("Email Address");

        var phoneField = new ValidatingTextField(userInput->
                (userInput.matches(phoneFormat)));
        phoneField.setPromptText("Phone Number");


        // Style the fields
        Stream.of(firstNameField,lastNameField, monthField, stateField,
                        dayField, yearField, addressField, cityField, zipField,
                        emailField, phoneField
                )
                .forEach(TextArea->
                        TextArea.getStyleClass().add("new-patient-fields")
                );
        // -----------------------------------------------------------------

        // --------------- Buttons -----------------
        // Same button style and font
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);
      
        // Go back button
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(buttonFont);

        // Handle Go Back Button
        goBackButton.setOnAction(event -> {
            try {
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        // Submit Button
        Button submitButton = new Button("Submit");
        String submitBtnStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;" +
                "-fx-pref-width: 200px;";
        submitButton.setStyle(submitBtnStyle);
        submitButton.setFont(buttonFont);
 
        // The save button will be enabled after all valid input is received by the user
        submitButton.disableProperty().bind(
                ((firstNameField.isValidInput.and
                        (lastNameField.isValidInput).and
                        (monthField.isValidInput).and
                        (dayField.isValidInput).and
                        (yearField.isValidInput).and
                        (addressField.isValidInput).and
                        (cityField.isValidInput).and
                        (stateField.isValidInput).and
                        (zipField.isValidInput).and
                        (emailField.isValidInput).and
                        (phoneField.isValidInput)
                ).not()));
        // On user click submit button
        submitButton.setOnAction(event->{
            // Initialize new form received from the fields after user input
            PatientInfoForm form = new PatientInfoForm(
                    firstNameField.getText(), lastNameField.getText(),
                    monthField.getText(), dayField.getText(), yearField.getText(),
                    addressField.getText(), cityField.getText(), stateField.getText(),
                    zipField.getText(), emailField.getText(), phoneField.getText()
            );

            // Store new patient information file
            Receptionist frontDesk = new Receptionist(dao, form);
            frontDesk.createPatientFile();


            // Switch stage to show generated ID to the user
            GridPane idLayout = new GridPane();
            idLayout.setPadding(new Insets (10));
            // Horizontal gap between fields
            idLayout.setHgap(5);
            // Vertical gap between fields
            idLayout.setVgap(20);

            Scene idScene = new Scene(idLayout, 300, 300);
            idScene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            Label stageLabel = new Label("Your Unique Patient ID");
            stageLabel.getStyleClass().add("patient-form-text");

            Label saveIdLbl = new Label("Save this ID for future use");
            saveIdLbl.getStyleClass().add("patient-form-text");

            var idField = new TextArea();
            idField.appendText(frontDesk.getCurrPatientID());
            idField.getStyleClass().add("new-patient-fields");
            idField.setStyle("-fx-focus-color: transparent;");
            idField.setEditable(false);

            // Buttons
            Button closeBtn = new Button("Close");
            String closeBtnStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;" +
                    "-fx-pref-width: 150px;";
            closeBtn.setStyle(closeBtnStyle);
            closeBtn.setFont(buttonFont);

            closeBtn.setOnAction(e->{
                try {
                    start(primaryStage);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            // Column 0
            GridPane.setConstraints(stageLabel, 0, 0, 3, 1, null, null, null, null, null);
            idLayout.getChildren().add(stageLabel);
            // Column 1
            GridPane.setConstraints(saveIdLbl, 0, 1, 3, 1, null, null, null, null, null);
            idLayout.getChildren().add(saveIdLbl);
            // Column 2
            GridPane.setConstraints(idField, 0, 2, 3, 1, null, null, null, null, null);
            idLayout.getChildren().add(idField);
            // Column 3
            GridPane.setConstraints(closeBtn, 0, 3, 3, 1, HPos.RIGHT, null, null, null, null);
            idLayout.getChildren().add(closeBtn);

            primaryStage.setScene(idScene);
        });
        // ----------------------------------------------------------------------------
        // Adding components to the Gridpane now
        // Column 0
        GridPane.setConstraints(patFormLbl, 0, 0, 2, 1, null, null, Priority.ALWAYS, null, null);
        formLayout.getChildren().add(patFormLbl);

        // Column 1
        formLayout.add(nameLbl, 0, 1);

        // Column 2
        formLayout.add(firstNameField, 0, 2);
        formLayout.add(lastNameField, 1, 2);
        
        // Column 3
        formLayout.add(dobLbl, 0, 3);
        
        // Column 4
        formLayout.add(monthField, 0, 4);
        formLayout.add(dayField, 1, 4);
        formLayout.add(yearField, 2, 4);
        
        // Column 5
        formLayout.add(addrLbl, 0, 5);
        
        // Column 6
        GridPane.setConstraints(addressField, 0, 6, 2, 1, null, null, null, null, null);
        formLayout.getChildren().add(addressField);
        
        // Column 7
        formLayout.add(cityField, 0, 7);
        formLayout.add(stateField, 1, 7);
        formLayout.add(zipField, 2, 7);
        
        // Column 8
        GridPane.setConstraints(contactLbl, 0, 8, 2, 1, null, null, null, null, null);
        formLayout.getChildren().add(contactLbl);
        
        // Column 9
        formLayout.add(emailField, 0, 9);
        formLayout.add(phoneField, 1, 9);
        
        // Column 10
        GridPane.setConstraints(submitButton, 0, 10, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
        formLayout.getChildren().add(submitButton);
        // Column 11
        GridPane.setConstraints(goBackButton, 0, 11, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
        formLayout.getChildren().add(goBackButton);
        
        //Scene patientScene = new Scene(patientGroup, 1000, 700);
        primaryStage.setScene(patientScene);
    }

 // Existing patient page
    private void existingPatientLogin(Stage primaryStage) {
        Stage patientStage = new Stage();
        patientStage.setTitle("Patient");

        
        // Same button style and font
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);
        
        // Text
        Text patientPageText = new Text("Patient Sign In");
        Text instructions = new Text("Please enter your Patient ID");

        // Text formatting and placement
        patientPageText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        patientPageText.setX(100);
        patientPageText.setY(100);
        instructions.setFont(Font.font("Verdana", 20));
        instructions.setFill(Color.GRAY);

        // Patient ID Text Field
        TextField patientIdField = new TextField("Patient ID");
        patientIdField.setStyle("-fx-text-fill: gray");
        patientIdField.setMaxWidth(300);
        patientIdField.setMinHeight(50);

        // Sign In button
        Button signIn = new Button("Sign In");
        signIn.setStyle(buttonStyle);
        signIn.setPrefWidth(300);
        signIn.setFont(buttonFont);

        // Go back button
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(buttonFont);

        VBox existingPatientBox = new VBox(50, patientPageText, instructions, patientIdField,
                                            signIn, goBackButton);
        existingPatientBox.setAlignment(Pos.CENTER);
        existingPatientBox.setPrefWidth(600);
        existingPatientBox.setPrefHeight(300);
        existingPatientBox.setLayoutX(200);
        existingPatientBox.setLayoutY(100);
        existingPatientBox.setPadding(new Insets(25));
        existingPatientBox.setStyle("-fx-border-color: black; -fx-border-width: 5px;"
                                    + " -fx-border-radius: 5px;");


        // Handle Sign In Button
        signIn.setOnAction(event -> {
          handlePatientLogin(patientIdField.getText(), primaryStage);
        });


        // Handle Go Back Button
        goBackButton.setOnAction(event -> {
            try {
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Group patientGroup = new Group(existingPatientBox);
        Scene patientScene = new Scene(patientGroup, 1000, 700);
        primaryStage.setScene(patientScene);
    }

    // Method to handle patient login
    private void handlePatientLogin(String patientId, Stage primaryStage) {
        // Check if the ID is 6 digits
        if (!patientId.matches("\\d{8}")) {
            showAlert(Alert.AlertType.ERROR, primaryStage, "Login Error", "Invalid ID: Please enter an 8-digit Patient ID.");
            return;
        }

        // Read the Patient's ID from a file title and verify
        try {
            Path path = Paths.get("data/" + patientId + ".txt");
            File patientFile = path.toFile();

            if (patientFile.exists()) {
                existingPatientProfile(patientFile,primaryStage);
            } else {
                // Show error if the ID is not found
                showAlert(Alert.AlertType.ERROR, primaryStage, "Login Error", "Invalid Patient ID: Please try again.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Patient Profile
    private void existingPatientProfile(File patientFile, Stage primaryStage) {
        Stage patientStage = new Stage();
        patientStage.setTitle("Patient");
        String patientName = null;

        try (BufferedReader br = new BufferedReader(new FileReader(patientFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Splitting the file line using commas
                String[] parts = line.split(",");

                // Assuming the first item is firstName, second item is lastName, etc.
                if (parts.length >= 4) { // Make sure there are enough items
                    String firstName = parts[0];
                    String lastName = parts[1];
                    patientName = firstName + " " + lastName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Same button style and font
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);

        // Text
        Text patientWelcomeText = new Text("Welcome, " + patientName);
        Text instructions = new Text("Please enter your Patient ID");

        // Text formatting and placement
        patientWelcomeText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        patientWelcomeText.setX(100);
        patientWelcomeText.setY(100);

        // Go back button
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(buttonFont);

        VBox existingPatientBox = new VBox(50, patientWelcomeText, goBackButton);
        existingPatientBox.setAlignment(Pos.CENTER);
        existingPatientBox.setPrefWidth(600);
        existingPatientBox.setPrefHeight(300);
        existingPatientBox.setLayoutX(200);
        existingPatientBox.setLayoutY(100);
        existingPatientBox.setPadding(new Insets(25));
        existingPatientBox.setStyle("-fx-border-color: black; -fx-border-width: 5px;"
                + " -fx-border-radius: 5px;");


        // Handle Sign In Button
        //signIn.setOnAction(event -> {
         //   handlePatientLogin(patientIdField.getText(), primaryStage);
        //});


        // Handle Go Back Button
        goBackButton.setOnAction(event -> {
            try {
                existingPatientLogin(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Group patientGroup = new Group(existingPatientBox);
        Scene patientScene = new Scene(patientGroup, 1000, 700);
        primaryStage.setScene(patientScene);
    }
    
    private void healthcarePage(Stage primaryStage) {
        // Title for the Healthcare Provider login scene
        Text healthcareSceneTitle = new Text("Healthcare Provider Login");
        healthcareSceneTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        // Style for the buttons
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);

        // Creating login buttons for each healthcare provider role
        Button nurseButton = new Button("Nurse");
        nurseButton.setStyle(buttonStyle);
        nurseButton.setFont(buttonFont);
        nurseButton.setPrefSize(325, 75);
        nurseButton.setOnAction(event -> nurseLoginPage(primaryStage));

        Button doctorButton = new Button("Doctor");
        doctorButton.setStyle(buttonStyle);
        doctorButton.setFont(buttonFont);
        doctorButton.setPrefSize(325, 75);
        doctorButton.setOnAction(event -> doctorLoginPage(primaryStage));

    

        // Go back button to return to the main scene
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(buttonFont);
        goBackButton.setPrefSize(325, 75);
        goBackButton.setOnAction(event -> {
            try {
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Layout for the healthcare provider buttons
        VBox healthcareButtonsBox = new VBox(20, // spacing between buttons
                healthcareSceneTitle, nurseButton, doctorButton, goBackButton);
        healthcareButtonsBox.setAlignment(Pos.CENTER);

        // Setting padding and border for the VBox
        healthcareButtonsBox.setPadding(new Insets(25));
        healthcareButtonsBox.setStyle("-fx-border-color: black; -fx-border-width: 5px;"
                + "-fx-border-radius: 5px; -fx-background-color: white;");

        // Creating and setting the scene
        Scene healthcareScene = new Scene(healthcareButtonsBox, 1000, 700);
        primaryStage.setScene(healthcareScene);
        primaryStage.setTitle("Healthcare Provider Login");
        primaryStage.show();
    }

    
    private void nurseLoginPage(Stage primaryStage) {
        // Title text
        Text nursePageText = new Text("Nurse Sign In");
        nursePageText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        // Instructions text
        Text instructions = new Text("Please enter your Nurse ID");
        instructions.setFont(Font.font("Verdana", 20));
        instructions.setFill(Color.GRAY);

        // Doctor ID Text Field
        TextField nurseIDField = new TextField();
        nurseIDField.setPromptText("Nurse ID");
        nurseIDField.setStyle("-fx-text-fill: black; -fx-pref-width: 300; -fx-pref-height: 50; -fx-font-size: 20;");

        // Sign In button
        Button signInButton = new Button("Sign In");
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white; -fx-font-size: 20;";
        signInButton.setStyle(buttonStyle);
        signInButton.setPrefWidth(300);
        signInButton.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        signInButton.setOnAction(event -> handleNurseLogin(nurseIDField.getText(), primaryStage));

        // Go back button
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        goBackButton.setPrefWidth(300);
        goBackButton.setOnAction(event -> {
            try {
                start(primaryStage); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // VBox layout for the sign-in components
        VBox signInBox = new VBox(20, nursePageText, instructions, nurseIDField, signInButton, goBackButton);
        signInBox.setAlignment(Pos.CENTER);
        signInBox.setPadding(new Insets(40));
        signInBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5;");

        // Center the VBox in the scene
        StackPane root = new StackPane(signInBox);
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Create the scene with the StackPane as the root
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor Sign In");
        primaryStage.show();
    }    
    
    
    
    private Object handleNurseLogin(String text, Stage primaryStage) {
        // TODO Auto-generated method stub
        return null;
    }

    private void doctorLoginPage(Stage primaryStage) {
        // Title text
        Text doctorPageText = new Text("Doctor Sign In");
        doctorPageText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        // Instructions text
        Text instructions = new Text("Please enter your Doctor ID");
        instructions.setFont(Font.font("Verdana", 20));
        instructions.setFill(Color.GRAY);

        // Doctor ID Text Field
        TextField doctorIdField = new TextField();
        doctorIdField.setPromptText("Doctor ID");
        doctorIdField.setStyle("-fx-text-fill: black; -fx-pref-width: 300; -fx-pref-height: 50; -fx-font-size: 20;");

        // Sign In button
        Button signInButton = new Button("Sign In");
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white; -fx-font-size: 20;";
        signInButton.setStyle(buttonStyle);
        signInButton.setPrefWidth(300);
        signInButton.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        signInButton.setOnAction(event -> handleDoctorLogin(doctorIdField.getText(), primaryStage));

        // Go back button
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        goBackButton.setPrefWidth(300);
        goBackButton.setOnAction(event -> {
            try {
                start(primaryStage); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // VBox layout for the sign-in components
        VBox signInBox = new VBox(20, doctorPageText, instructions, doctorIdField, signInButton, goBackButton);
        signInBox.setAlignment(Pos.CENTER);
        signInBox.setPadding(new Insets(40));
        signInBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5;");

        // Center the VBox in the scene
        StackPane root = new StackPane(signInBox);
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Create the scene with the StackPane as the root
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor Sign In");
        primaryStage.show();
    }

    
    // Method to handle doctor login
    private void handleDoctorLogin(String id, Stage primaryStage) {
        // Check if the ID is 6 digits
        if (!id.matches("\\d{6}")) {
            showAlert(Alert.AlertType.ERROR, primaryStage, "Login Error", "Invalid ID: Please enter a 6-digit ID.");
            return;
        }
        
        // Read the doctor IDs from a file and verify
        try {
            Path path = Paths.get("doctors.txt");
            boolean isValid = Files.lines(path).anyMatch(line -> line.equals(id));

            if (isValid) {
                // If the ID is valid, proceed to the doctor page (to be implemented)
                goToDoctorPage(primaryStage);
            } else {
                // Show error if the ID is not found
                showAlert(Alert.AlertType.ERROR, primaryStage, "Login Error", "Invalid ID: ID not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, primaryStage, "Login Error", "An error occurred while verifying ID.");
        }
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


    
    
 // Placeholder method to go to the doctor page
    private void goToDoctorPage(Stage primaryStage) {
        // TODO: Implement transition to the doctor's page
    }
  
    
   

    public static void main(String[] args) {
        launch();
    }

}
