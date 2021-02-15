package com.c2p.javafxApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public Label welcomeLabel;

	@FXML
	public ChoiceBox<String> choiceBox;

	@FXML
	public TextField userInputField;

	@FXML
	public Button convertBtn;

	// constant declaration
	private static final String C_TO_F_TEXT = "Celsius to Fahrenheit", F_TO_C_TEXT = "Fahrenheit to Celsius";

	private boolean isC_TO_F = true;

	@Override
	public void initialize(URL urlLocation, ResourceBundle resources) {

		choiceBox.getItems().add(C_TO_F_TEXT);
		choiceBox.getItems().add(F_TO_C_TEXT);

		choiceBox.setValue(C_TO_F_TEXT);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
			// System.out.println("Old Value: " + oldValue + "\nNew Value: " + newValue);

			// checking whether C_TO_F is selected or not
			isC_TO_F = newValue.equals(C_TO_F_TEXT);

		});

		convertBtn.setOnAction(event -> {
			// System.out.println("Button Clicked.");
			convert();
		});

	}

	private void convert() {

		String input = userInputField.getText();
		float resultTemp, inputTemp;
		try {
			inputTemp = Float.parseFloat(input);
		} catch (Exception e1) {
			warnUser();
			return;
		}

		if (isC_TO_F) {
			resultTemp = (inputTemp * 9 / 5) + 32;
		} else {
			resultTemp = (inputTemp - 32) * 5 / 9;
		}
		display(resultTemp);
	}

	private void warnUser() {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred.");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please enter a valid temperature.");
		alert.show();

	}

	private void display(float resultTemp) {

		char unit = isC_TO_F ? 'F' : 'C';

		//System.out.println("The new Temperature is " + resultTemp + ' ' + unit);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result Temperature.");
		alert.setContentText("The new Temperature is " + resultTemp + ' ' + unit);
		alert.show();
	}

	public void newConversion() {
		isC_TO_F = true;
		choiceBox.setValue(C_TO_F_TEXT);
		userInputField.setText(null);
	}
}
