package main.com.exceptions;

import javafx.scene.control.Alert;

public class InvalidInputException extends Exception{
	private String whatShouldDo;
	private String yourProblem;
	public InvalidInputException(String should,String problem) {
		whatShouldDo=should;
		yourProblem=problem;
	}
	public void showMessage() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(whatShouldDo);
		alert.setContentText(yourProblem);
		alert.showAndWait();
	}
}
