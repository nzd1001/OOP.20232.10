package main.com.controllers;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
public class MainMenuController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private ChoiceBox<String> algo_choice_box=new ChoiceBox<String>();
    @FXML private Button start_button=new Button();
    @FXML private Button quit_button=new Button();
    @FXML private Button help_button=new Button();
    private static int selected_sort;
    public static int getSortIndex() {
    	return selected_sort;
    }
    public void initialize() throws IOException{
    	selected_sort=0;
         start_button.setOnAction(e->{
             try{
                 switch_scene2(e);}
             catch(IOException err){
                 System.err.println("Error!");
             }});
         quit_button.setOnAction(e->confirmQuit(e));
         help_button.setOnAction(e->{
        	 try{
        		 showHelpScene();}
             catch(IOException err){
                 System.err.println("Error!");
             }
         });
         choiceBoxInitialize();
    }
    public void choiceBoxInitialize() {
    	algo_choice_box.getItems().addAll("Insertion Sort","Bubble Sort","Quick Sort");
        algo_choice_box.getSelectionModel().selectFirst(); 
        algo_choice_box.setOnAction(e->{
        	String current_algo=algo_choice_box.getValue();
    		if (current_algo=="Insertion Sort") {selected_sort=0;}
    		else if (current_algo=="Bubble Sort") {selected_sort=1;}
    		else {selected_sort=2;}
        });
    }
     public void switch_scene2(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/visualizer_scene.fxml")); 
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
     }
     private void confirmQuit(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Exit Confirmation");
        confirmation.setContentText("Are you sure you want to quit?");
        confirmation.showAndWait();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        if (confirmation.getResult() == ButtonType.OK) {
            stage.close();
        }
    }
    private void showHelpScene() throws IOException{
         // Create a new Stage and Scene for Help 
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/help_scene.fxml")); 
	     Stage helpStage = new Stage();
	     Scene helpScene=new Scene(loader.load());
	     helpStage.setScene(helpScene);
	     helpStage.setResizable(false);
	     helpStage.setTitle("Help");
	     helpStage.show();
     }
}
