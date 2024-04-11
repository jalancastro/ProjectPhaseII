package com.cse360.medicalproject.projectphaseii;

import javafx.scene.control.TextField;
import java.util.function.Predicate;
import javafx.beans.property.SimpleBooleanProperty;


public class ValidatingTextField extends TextField{
	// data
	private final Predicate<String> validating;
	protected SimpleBooleanProperty isValidInput = new SimpleBooleanProperty();
	
	// Constructor
	ValidatingTextField(Predicate<String> validating){
		// User input to validate
		this.validating = validating;
		// Listen to user input contained in the text field
		textProperty().addListener((userInput, oldInput, newInput)->{
			isValidInput.set(validating.test(newInput));
		});
		isValidInput.set(validating.test(""));
	}
	
}
