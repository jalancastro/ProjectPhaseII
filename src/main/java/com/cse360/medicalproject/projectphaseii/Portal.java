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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Portal extends Application {

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
        existingPatientButton.setOnAction(event -> patientPage(primaryStage));
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
			(userInput.matches(("(^[1-9]$)|(^1[0-2]$)")))); 	// (m) 1-12
		monthField.setPromptText("Month");
		
		var dayField = new ValidatingTextField(userInput->
			(userInput.matches(("[1-9]|[12]\\d|3[01]$")))); 	// (d) 1-31 
		dayField.setPromptText("Day");
		
		var yearField = new ValidatingTextField(userInput->
			(userInput.matches(("^\\d{4}$"))));					// (yyyy)
		yearField.setPromptText("Year");
		
		// Address fields
		var addressField = new ValidatingTextField(userInput->true); // any string
		addressField.setPromptText("Street Address");
		
		var cityField = new ValidatingTextField(userInput->true); // any string
		cityField.setPromptText("City");
		
		var stateField = new ValidatingTextField(userInput->true); // any string
		stateField.setPromptText("State");
		
		var zipField = new ValidatingTextField(userInput->
				(userInput.matches("^\\d{3,10}")));			//3-10-digit integer
		zipField.setPromptText("Zip/Postal Code");
		
		// Initializing regex strings
		// Email regex format: {anything}@{anything}.{any 2-4 length word}
		String emailFormat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		// Phone regex format: xxx-xxx-xxxx, x-xxx-xxx-xxxx, with () variations
		String phoneFormat = "^\\s*(?:\\+?(\\d{1,3}))?"
				+ "[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*"
				+ "(\\d{4})(?: *x(\\d+))?\\s*$"
				;
		
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
        //goBackButton.setLayoutX(300);
       // goBackButton.setLayoutY(700);
        
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
        submitButton.getStyleClass().add("form-btns");
 
        // The save button will be enabled after all valid input is input by the user
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
        	// Data received from the fields after user input
        	String inputFirstName = firstNameField.getText();
			String inputLastName = lastNameField.getText();
			int inputMonth = Integer.valueOf(monthField.getText());
			int inputDay = Integer.valueOf(dayField.getText());
			int inputYear = Integer.valueOf(yearField.getText());
			String inputAddress = addressField.getText();
			String inputCity = cityField.getText();
			String inputState = stateField.getText();
			int inputZip = Integer.valueOf(zipField.getText());
			String inputEmail = emailField.getText();
			String inputPhone = phoneField.getText();
			
			// Implementation to generate ID 
			
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
	        idField.appendText("PlaceHolder for ID to display");
	        idField.getStyleClass().add("new-patient-fields");
	        idField.setStyle("-fx-focus-color: transparent;");
	        idField.setEditable(false);
	        
	        // Buttons
	        Button closeBtn = new Button("Close");
	        closeBtn.getStyleClass().add("form-btns");
	        closeBtn.setStyle("-fx-pref-width: 100;");
	        
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
        
        //Scene patientScene = new Scene(patientGroup, 1000, 700);
        primaryStage.setScene(patientScene);
    }

    // Existing patient page
    private void patientPage(Stage primaryStage) {
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
        /*signIn.setOnAction(event -> {
          FIX ME
        });
        */

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
    
    // Healthcare Provider page
    private void healthcarePage(Stage primaryStage) {
        Stage healthcareStage = new Stage();
        healthcareStage.setTitle("Healthcare Provider");
        
        // Same button style and font
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);
        
        // Placeholder Text
        Text healthcarePageText = new Text("This is the healthcare provider page");
        healthcarePageText.setFont(Font.font("Verdana", FontWeight.BOLD, 25)); 
        healthcarePageText.setX(250);
        healthcarePageText.setY(350);
        
        // Go back button
        Button goBackButton = new Button("Go Back");
        goBackButton.setStyle(buttonStyle);
        goBackButton.setFont(buttonFont);
        goBackButton.setLayoutX(800);
        goBackButton.setLayoutY(600);
        
        // Handle Go Back Button
        goBackButton.setOnAction(event -> {
            try {
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Group healthcareGroup = new Group(healthcarePageText, goBackButton);
        Scene healthcareScene = new Scene(healthcareGroup, 1000, 700);
        primaryStage.setScene(healthcareScene);
    }


    public static void main(String[] args) {
        launch();
    }

}
