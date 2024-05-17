package apps.controllers;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class main_menu_controller {
    private ChoiceBox<String> algo_choice_box=new ChoiceBox<String>();
    private Button start_button=new Button();
    public void initialize(){
        algo_choice_box.getItems().addAll("Bubble Sort", "Insertion Sort", "Quick Sort");
        algo_choice_box.getSelectionModel().selectFirst(); 
        algo_choice_box.setStyle("-fx-font-size: 12px;");
    }
}
