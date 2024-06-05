package main.com.handlers;
import main.com.exceptions.*;
import java.util.Random;

import javafx.scene.control.Alert;

public class DataHandler {
	private int[] data;
	public int[] getData() {
		return data;
	}
	public void inputData(String inputText) throws InvalidInputException{
		//System.out.println(inputText);
	    String[] inputArray = inputText.split(",");
	    //System.out.println(inputArray[0]+inputArray[1]+inputArray[2]);
	    //length = inputArray.length;
	    data = new int[inputArray.length];
	    //boolean isValidInput = true;
	    for (int j = 0; j<inputArray.length; j++) {
	        try {
	            String num = inputArray[j].trim(); // Trim leading/trailing whitespace
	        	//for (int i = 0; i < inputArray.length; i++) {
	            data[j] = Integer.parseInt(num);
	            if (data[j]<1 || data[j]>50) {
	            	throw new InvalidInputException("Please enter integers that is between 1 and 50.","Your input value is not valid!");
	            }
	        }
	         catch (NumberFormatException e) {
	            
	            throw new InvalidInputException("Please enter only comma-separated numbers.","The input you provided contains non-numeric characters.");
	        }
	    }    
	    if (inputArray.length>100) {
	    	throw new InvalidInputException("Please enter an array that has length no exceeding 100.","Your array has too many number!");
	    }
    }
	public void create_random_data(){
        Random random = new Random();
        int l = random.nextInt(98)+3;
        data=new int[l];
        for (int i = 0; i < l; i++) {
            data[i] = random.nextInt(50)+1;
          }
    }
}
