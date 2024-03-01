package com.cse360.medicalproject.projectphaseii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Portal extends Application {

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        // Interface
        BorderPane border = new BorderPane();
        VBox loginButtons = new VBox(8);

        Text sceneTitle = new Text("Pediatric Health");
        sceneTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextArea billText = new TextArea();
        billText.setPrefSize(100,100);
        border.setTop(sceneTitle);
        border.setCenter(loginButtons);
        loginButtons.setAlignment(Pos.CENTER);
        loginButtons.setPrefSize(200, 400);

        // Initiating Titles for Labels
        Text welcome = new Text("Welcome");
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        // Creating the Buttons
        Button newPatient = new Button("New Patient");
        Button existingPatient = new Button("Existing Patient");
        Button healthcareProvider = new Button("Healthcare Provider");

        // Setting the Layout
        loginButtons.getChildren().addAll(newPatient, existingPatient, healthcareProvider);
        VBox.setMargin(loginButtons, new Insets(100, 100, 100, 50));

        // Button Actions
        /*order.setOnAction(e -> {

            // Initiating Price Values
            double esPrice = 0;
            double csPrice = 0;
            double bagelPrice = 0;
            double psPrice = 0;
            double btPrice = 0;
            double gtPrice = 0;
            double coffeePrice = 0;
            double ojPrice = 0;

            // Checkbox Actions
            if (esButton.isSelected()) {
                esPrice = 7.99;
                billText.appendText("Egg Sandwich $" + esPrice + "\n");
            }
            if (csButton.isSelected()) {
                csPrice = 9.99;
                billText.appendText("Chicken Sandwich $" + csPrice + "\n");
            }
            if (bagelButton.isSelected()) {
                bagelPrice = 2.50;
                billText.appendText("Bagel $" + bagelPrice + "\n");
            }
            if (psButton.isSelected()) {
                psPrice = 4.49;
                billText.appendText("Potato Salad $" + psPrice + "\n");
            }
            if (btButton.isSelected()) {
                btPrice = 1.25;
                billText.appendText("Black Tea $" + btPrice + "\n");
            }
            if (gtButton.isSelected()) {
                gtPrice = 0.99;
                billText.appendText("Green Tea $" + gtPrice + "\n");
            }
            if (coffeeButton.isSelected()) {
                coffeePrice = 1.99;
                billText.appendText("Coffee $" + coffeePrice + "\n");
            }
            if (ojButton.isSelected()) {
                ojPrice = 2.25;
                billText.appendText("Orange Juice $" + ojPrice + "\n");
            }
            billText.appendText("------------------\n");
            double sum = esPrice + csPrice + bagelPrice + psPrice + btPrice + gtPrice + coffeePrice + ojPrice;
            billText.appendText("Subtotal: $" + decimalFormat.format(sum));

            // If cancel is clicked after Order button is clicked
            cancel.setOnAction(f -> {
                billText.clear();
            });


        });*/


        Scene scene = new Scene(border, 600, 300, Color.WHITE);

        border.setTop(sceneTitle);
        border.setAlignment(sceneTitle,Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
