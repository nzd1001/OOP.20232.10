package apps.controllers;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import apps.algorithms.*;
import apps.components.Bar;
import apps.components.BarsCollection;
import java.util.*;
public class visualizer_scene_controller {
    @FXML private Button sort_button=new Button();
    @FXML private Pane displaySort=new Pane();
    public void initialize(){
        int[] data={10,6,57,82,41,35,19};
        BarsCollection collection=new BarsCollection(data);
        Bar[] bars=collection.initialize();
        displaySort.getChildren().addAll(Arrays.asList(bars));
        sort_button.setOnAction(e->{
          new BubbleSort().sort(bars);
        });
    }
}
