package main.resources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.stage.Stage;
import main.apps.algorithms.*;
import main.apps.components.Bar;
import main.apps.components.BarsCollection;
import java.io.IOException;
import java.net.URL;
import java.util.*;
public class visualizer_scene_controller {
    @FXML private Button sort_button=new Button();
    @FXML private Pane displaySort=new Pane();
    @FXML private Button randomize_button=new Button();
    @FXML private Button back_button=new Button();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Bar[] bars;
    public void initialize() throws IOException{
        //int[] data={10,6,57,82,41,35,19};
    	randomize_button.setOnAction(e->{this.bars=create_bars();});
        sort_button.setOnAction(ee->{
            if(bars==null||bars.length==0){
                showInputDataAlert();
            }
            else{
                new QuickSort().sort(bars);
            }
            
        });
        back_button.setOnAction(e->{
            try{
                switch_scene1(e);}
            catch(IOException err){
                System.err.println("Error!");
            }});
    }
    public Bar[] create_bars(){
        Random random = new Random();
        int[] data=new int[7];
        for (int i = 0; i < data.length; i++) {
            
            data[i] = random.nextInt(50)+1;
          }
        BarsCollection collection=new BarsCollection(data);
        Bar[] bars=collection.initialize();
        displaySort.getChildren().clear();
        displaySort.getChildren().addAll(Arrays.asList(bars));
        return bars;
    }
    public void switch_scene1(ActionEvent event) throws IOException{
        URL fxmlpathUrl=getClass().getResource("view/main_menu.fxml");
        //FXMLLoader loader1 = new FXMLLoader(getClass().getResource("resources/view/main_menu.fxml")); 
        FXMLLoader loader = new FXMLLoader(fxmlpathUrl); 
        root=loader.load();    
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showInputDataAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing Input Data!");
        alert.setHeaderText("Please Enter Data ");
        alert.setContentText("You cannot sort without any data. Please enter data or randomize data before sorting.");
        alert.showAndWait();
    }   
}
