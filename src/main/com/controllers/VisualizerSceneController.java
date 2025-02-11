package main.com.controllers;
import main.com.exceptions.*;
import main.com.utilization.*;
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

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.util.*;
public class VisualizerSceneController {
	@FXML private Button input_button = new Button();
	@FXML private TextField input_textfield = new TextField();
    @FXML private Button sort_button=new Button();
    @FXML private BorderPane displaySortPane=new BorderPane();
    @FXML private Button randomize_button=new Button();
    @FXML private Button back_button=new Button();
    @FXML private Label speed_label=new Label();
    @FXML private Slider speed_slider=new Slider();
    @FXML private Label num_label=new Label();
    @FXML private Slider num_slider=new Slider();
    @FXML private Button reset_button=new Button();
    @FXML private ChoiceBox<String> algo_box=new ChoiceBox();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Visualizer visualizer=new Visualizer(new DataHandler());
    private String[] algo_list= {"Insertion Sort","Bubble Sort","Quick Sort"};
    public void initialize() throws IOException{
    	mapSort(algo_list[MainMenuController.getSortIndex()]);
        numSliderInitialize();
    	randomize_button.setOnAction(e->randomizeButtonHandler());
        reset_button.setOnAction(e->resetBars());
        back_button.setOnAction(e->backButtonHandler(e));
        input_button.setOnAction(e->inputButtonHandler());
        sort_button.setOnAction(e->sortButtonHandler());
        speedSliderInitialize();
        choiceBoxInitialize();
        paneResize();
    }
    public void paneResize(){
        displaySortPane.widthProperty().addListener((observable,oldValue,newValue)->{
            displayBars();
        });
        displaySortPane.heightProperty().addListener((observable,oldValue,newValue)->{
            displayBars();
        });
    }
    public void resetBars() {
			displayBars();
    }
    public void numSliderInitialize(){
        int default_num=50; //Bar.getSpeed();
    	num_slider.setMin(1);
        num_slider.setMax(100);
        num_slider.setValue(default_num);
        num_slider.setBlockIncrement(10);
        num_slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
        	int current_num = newValue.intValue();
        	//Bar.setSpeed(current_speed);
            num_label.setText(String.format("n: %d", current_num));});
        num_label.setText(String.format("n: %d",default_num));
    }
    public void randomizeButtonHandler() {
    	visualizer.getDataHandler().create_random_data((int)num_slider.getValue());
		displayBars();
    }
    public void backButtonHandler(ActionEvent e) {
    	try{
            switch_scene1(e);}
        catch(IOException err){
            System.err.println("Error!");}
    }
    public void inputButtonHandler() {
    	String inputText = input_textfield.getText();
    	try {
    		visualizer.getDataHandler().inputData(inputText);		
    		displayBars();
    		}
    	catch(InvalidInputException err) {
    		err.showMessage();
    	}   
    }
    public void sortButtonHandler() {
		try { letSort();}
		catch(MissingInputException err) {
			err.showMessage();
		}
    }
    public void letSort() throws MissingInputException {
        if(visualizer.getBars()==null||visualizer.getBars().length==0){
            throw new MissingInputException();
        }
        else{
        	resetBars();
        	SequentialTransition sortingAnimation=visualizer.sort();
        	sortingAnimation.rateProperty().bind(speed_slider.valueProperty());
        	sortingAnimation.play();
        }
    }
    public void speedSliderInitialize() {
    	float default_speed=5; //Bar.getSpeed();
    	speed_slider.setMin(0.5);
        speed_slider.setMax(50.0);
        speed_slider.setValue(default_speed);
        speed_slider.setBlockIncrement(0.5);
        speed_slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
        	double current_speed = newValue.doubleValue();
        	//Bar.setSpeed(current_speed);
            speed_label.setText(String.format("Speed: %.1f", current_speed));});
        speed_label.setText(String.format("Speed: %.1f",default_speed));
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
    	if (algo_name=="Insertion Sort") {visualizer.setAlgo(new InsertionSort());}
		else if (algo_name=="Bubble Sort") {visualizer.setAlgo(new BubbleSort());}
		else {visualizer.setAlgo(new QuickSort());}
    }
    public void displayBars() {
        int[] data=visualizer.getDataHandler().getData();
		if (!(data==null||data.length==0)) {
            visualizer.create_bars(displaySortPane);
            Bar[] bars=visualizer.getBars();
            displaySortPane.getChildren().clear();
            Group barGroup=new Group();
            barGroup.getChildren().addAll(Arrays.asList(bars));
            displaySortPane.setBottom(barGroup);
            BorderPane.setAlignment(barGroup,Pos.CENTER); }
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