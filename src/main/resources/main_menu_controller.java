package main.resources;
import java.io.IOException;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
public class main_menu_controller {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private ChoiceBox<String> algo_choice_box=new ChoiceBox<String>();
    @FXML private Button start_button=new Button();
    @FXML private Button quit_button=new Button();
    public void initialize() throws IOException{
        algo_choice_box.getItems().addAll("Bubble Sort", "Insertion Sort", "Quick Sort");
        algo_choice_box.getSelectionModel().selectFirst(); 
        algo_choice_box.setStyle("-fx-font-size: 12px;");
         start_button.setOnAction(e->{
             try{
                 switch_scene2(e);}
             catch(IOException err){
                 System.err.println("Error!");
             }});
         quit_button.setOnAction(e->confirmQuit(e));
    }
     public void switch_scene2(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("view/visualizer_scene.fxml")); 
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
}
