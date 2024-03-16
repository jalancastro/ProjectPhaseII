package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Portal extends Application {

    public void start(Stage primaryStage) throws Exception {

        // Creating sign in buttons
        Button newPatient = new Button("New Patient");
        Button existingPatient = new Button("Existing Patient");
        Button healthcareProvider = new Button("Healthcare Provider");

        // Set preferred width for buttons
        double preferredWidth = 200;
        newPatient.setPrefWidth(preferredWidth);
        existingPatient.setPrefWidth(preferredWidth);
        healthcareProvider.setPrefWidth(preferredWidth);

        // Creating VBox layout for buttons
        VBox vbox = new VBox(10); // spacing between buttons
        vbox.getChildren().addAll(newPatient, existingPatient, healthcareProvider);
        vbox.setPadding(new Insets(10));

        // Centering buttons within the VBox
        vbox.setFillWidth(true);
        vbox.setAlignment(Pos.CENTER);

        // Creating "Sign In" title for text buttons box
        Text signInTitle = new Text("Sign In");
        signInTitle.setFont(new Font(20));

        // Creating a VBox to contain "Sign In" instruction and buttons
        VBox signInBox = new VBox();
        signInBox.getChildren().addAll(signInTitle, vbox);
        signInBox.setPadding(new Insets(20));
        signInBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");

        // Creating a BorderPane layout for the scene
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(signInBox);

        // Setting margins to center signInBox in the scene
        BorderPane.setMargin(signInBox, new Insets(50));

        // Creating a VBox to contain the name of the portal/place
        VBox titleBox = new VBox();
        Text appTitle = new Text("PediatricHealth");
        appTitle.setFont(new Font(18));
        titleBox.getChildren().add(appTitle);
        borderPane.setTop(titleBox);

        // Creating a scene
        Scene scene = new Scene(borderPane, 400, 300);

        // Setting minimum width and height of the stage
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);

        // Setting the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("PediatricHealth");
        primaryStage.setResizable(false); // Page can not be resized
        primaryStage.show();

        // Action when buttons are clicked
        newPatient.setOnAction(event -> patientPage());
        existingPatient.setOnAction(event -> patientPage());
        healthcareProvider.setOnAction(event -> healtcarePage());
    }

    // Patient page
    private void patientPage() {
        Stage patientStage = new Stage();
        BorderPane borderPane = new BorderPane();
        Text text = new Text("This is the patient page");
        borderPane.setCenter(text);
        Scene scene = new Scene(borderPane, 200, 100);
        patientStage.setScene(scene);
        patientStage.setTitle("Patient");
        patientStage.show();
    }
    // Healthcare Provider page
    private void healtcarePage() {
        Stage healthcareStage = new Stage();
        BorderPane borderPane = new BorderPane();
        Text text = new Text("This is the healthcare page");
        borderPane.setCenter(text);
        Scene scene = new Scene(borderPane, 200, 100);
        healthcareStage.setScene(scene);
        healthcareStage.setTitle("Healthcare Provider");
        healthcareStage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}
