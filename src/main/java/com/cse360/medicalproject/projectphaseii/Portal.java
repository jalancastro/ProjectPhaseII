package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        Stage patientStage = new Stage();
        patientStage.setTitle("New Patient");
        
        // Same button style and font
        String buttonStyle = "-fx-background-color: #4473c5; -fx-text-fill: white;";
        Font buttonFont = Font.font("Verdana", FontWeight.NORMAL, 25);
        
        // Placeholder Text
        Text patientPageText = new Text("This is the new patient page");
        patientPageText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        patientPageText.setX(300);
        patientPageText.setY(350);
        
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
        
        Group patientGroup = new Group(patientPageText, goBackButton);
        Scene patientScene = new Scene(patientGroup, 1000, 700);
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
