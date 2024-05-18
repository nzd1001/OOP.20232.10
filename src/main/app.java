package main;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;
public class app extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlpathUrl=getClass().getResource("resources/view/main_menu.fxml");
    //FXMLLoader loader1 = new FXMLLoader(getClass().getResource("resources/view/main_menu.fxml")); 
    FXMLLoader loader2 = new FXMLLoader(fxmlpathUrl); 

    //Parent main_menu = loader1.load();  
    Parent visualizer_scene=loader2.load();
    //Scene scene1 = new Scene(main_menu);
    Scene scene2=new Scene(visualizer_scene);
    primaryStage.setScene(scene2);
    primaryStage.setTitle("Visualizer");
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
