package main.com.controllers;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.stage.Stage;
import main.com.algorithms.*;
import main.com.components.*;
import main.com.handlers.*;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.util.*;
public class VisualizerSceneController {
	@FXML private Button ok_button = new Button();
	@FXML private TextField input_textfield = new TextField();
    @FXML private Button sort_button=new Button();
    @FXML private BorderPane displaySort=new BorderPane();
    @FXML private Button randomize_button=new Button();
    @FXML private Button back_button=new Button();
    @FXML private Label speed_label=new Label();
    @FXML private Slider speed_slider=new Slider();
    @FXML private Button reset_button=new Button();
    @FXML private ChoiceBox<String> algo_box=new ChoiceBox();
    private boolean isSorting=false;
    private static int[] data;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Bar[] bars;
    private Sort current_sort;
    private DataHandler input= new DataHandler();
    private String[] algo_list= {"Insertion Sort","Bubble Sort","Quick Sort"};
    public void initialize() throws IOException{
    	mapSort(algo_list[MainMenuController.getSortIndex()]);
    	randomize_button.setOnAction(e->{
    		data=input.create_random_data();
    		this.bars=create_bars(data);
    	});
        sort_button.setOnAction(e->{
            if(bars==null||bars.length==0){
                input.showMissingDataAlert();
            }
            else{
            	resetBars();
            	SequentialTransition sortingAnimation=current_sort.sort(bars);
            	sortingAnimation.play();
            	/*sort_button.setDisable(true);
            	sortingAnimation.setOnFinished(ee-> {
	        		 sort_button.setDisable(false);  // Re-enable button after sorting finishes
	        	});*/
            }
        });
        reset_button.setOnAction(e->resetBars());
        back_button.setOnAction(e->{
            try{
                switch_scene1(e);}
            catch(IOException err){
                System.err.println("Error!");
            }});
        ok_button.setOnAction(eee->{
        	String inputText = input_textfield.getText();
        	data = input.inputData(inputText);
        	boolean isValidInput=input.getValid();
        	if(isValidInput) {
        		this.bars=create_bars(data);}
        	else {
        		input.showInvalidInputDataAlert();
        	}
        });
        speedSliderInitialize();
        choiceBoxInitialize();
    }
    public void resetBars() {
		if (!(data==null||data.length==0)) {
			this.bars=create_bars(data);}
    }
    public void speedSliderInitialize() {
    	int default_speed=Bar.getSpeed();
    	speed_slider.setMin(100);
        speed_slider.setMax(2000);
        speed_slider.setValue(default_speed);
        speed_slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
        	int current_speed = (int) newValue.doubleValue();
        	Bar.setSpeed(current_speed);
            speed_label.setText("Speed: " + current_speed);});
        speed_label.setText(String.format("Speed:%d",default_speed));
    }
    public void choiceBoxInitialize(){	
    	algo_box.getItems().addAll(algo_list);
    	algo_box.getSelectionModel().select(algo_list[MainMenuController.getSortIndex()]);
    	algo_box.setOnAction(e->{
    		String current_algo_name=algo_box.getValue();
    		mapSort(current_algo_name);
    	});
    }
    public void mapSort(String algo_name) {
    	if (algo_name=="Insertion Sort") {current_sort=new InsertionSort();}
		else if (algo_name=="Bubble Sort") {current_sort=new BubbleSort();}
		else {current_sort=new QuickSort();}
    }
    public Bar[] create_bars(int[] intArray) {
    	BarsCollection collection=new BarsCollection(intArray);
        Bar[] bars=collection.initialize(displaySort);
        displaySort.getChildren().clear();
        Group barGroup=new Group();
        barGroup.getChildren().addAll(Arrays.asList(bars));
        /*displaySort.getChildren().add(barGroup);*/
        displaySort.setBottom(barGroup);
        displaySort.setAlignment(barGroup,Pos.CENTER);
        return bars;
    }
    public void switch_scene1(ActionEvent event) throws IOException{
        //FXMLLoader loader1 = new FXMLLoader(getClass().getResource("resources/view/main_menu.fxml")); 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/main_menu.fxml")); 
        root=loader.load();    
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}