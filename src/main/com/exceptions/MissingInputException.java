package main.com.exceptions;

import javafx.scene.control.Alert;

public class MissingInputException extends Exception{
	public void showMessage() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing Input Data!");
        alert.setHeaderText("Please Enter Data ");
        alert.setContentText("You cannot sort without any data. Please enter data or randomize data before sorting.");
        alert.showAndWait();
	}
}
