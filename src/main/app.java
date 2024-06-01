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
    
    //FXMLLoader loader1 = new FXMLLoader(getClass().getResource("resources/view/main_menu.fxml")); 
    FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/view/visualizer_scene.fxml")); 
    //Parent main_menu = loader1.load();  
    Parent p=loader.load();
    //Scene scene1 = new Scene(main_menu);
    Scene scene=new Scene(p);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Visualizer");
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
