package main.com.handlers;

import java.util.Random;

import javafx.scene.control.Alert;

public class DataHandler {
	private boolean valid;
	private String[] errorContent=new String[2];
	public boolean getValid() {
		return valid;
	}
	public int[] inputData(String inputText) {
		valid=true;
		//System.out.println(inputText);
	    String[] inputArray = inputText.split(",");
	    //System.out.println(inputArray[0]+inputArray[1]+inputArray[2]);
	    //length = inputArray.length;
	    int[] intArray = new int[inputArray.length];
	    //boolean isValidInput = true;
	    for (int j = 0; j<inputArray.length; j++) {
	        try {
	            String num = inputArray[j].trim(); // Trim leading/trailing whitespace
	        	//for (int i = 0; i < inputArray.length; i++) {
	            intArray[j] = Integer.parseInt(num);
	            if (intArray[j]<1 || intArray[j]>50) {
	            	valid=false;
	            	matchError("InvalidValue");
	            	return null;
	            }
	        }
	         catch (NumberFormatException e) {
	            valid = false;
	            matchError("WrongInputFormat");
	            return null;
	        }
	    }    
	    if (inputArray.length>100) {
	    	valid=false;
	    	matchError("Overlong");
	    	return null;
	    }
	    return intArray;
    }
	public void matchError(String er) {
		if (er.equals("WrongInputFormat")) {
			errorContent[0]="Please enter only comma-separated numbers.";
			errorContent[1]="The input you provided contains non-numeric characters.";
		}
		else if (er.equals("InvalidValue")) {
			errorContent[0]="Please enter integers that is between 1 and 50";
			errorContent[1]="Your input value is not valid";
		}
		else if (er.equals("Overlong")){
			errorContent[0]="Please enter an array that has length no exceeding 100";
			errorContent[1]="Your array has too many numbers";
		}
	}
	public int[] create_random_data(){
        Random random = new Random();
        int l = random.nextInt(98)+3;
        int[] data=new int[l];
        for (int i = 0; i < l; i++) {
            data[i] = random.nextInt(50)+1;
          }
       return data;
    }
	public void showMissingDataAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing Input Data!");
        alert.setHeaderText("Please Enter Data ");
        alert.setContentText("You cannot sort without any data. Please enter data or randomize data before sorting.");
        alert.showAndWait();
    }   
	 
    public void showInvalidInputDataAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(errorContent[0]);
		alert.setContentText(errorContent[1]);
		alert.showAndWait();
	}
}
