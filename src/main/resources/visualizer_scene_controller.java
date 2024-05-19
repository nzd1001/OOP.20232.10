package main.resources;
import javafx.event.ActionEvent;
import java.util.Random;
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
        try{sort_button.setOnAction(ee->{
            System.out.println(bars.length);
            new QuickSort().sort(bars);
          });}
          catch(Exception ee){
              //switch_scene1(e);
              System.out.println("Chua co mang sort con cac");
          }
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
        displaySort.getChildren().addAll(Arrays.asList(bars));
        System.out.println(bars[0].getValue());
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
}
