package main.com.controllers;
import main.com.exceptions.*;
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
	@FXML private Button input_button = new Button();
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
    		input.create_random_data();
    		this.bars=create_bars();
    	});
        reset_button.setOnAction(e->resetBars());
        back_button.setOnAction(e->{
            try{
                switch_scene1(e);}
            catch(IOException err){
                System.err.println("Error!");
            }});
        inputButtonInitialize();
        sortButtonInitialize();
        speedSliderInitialize();
        choiceBoxInitialize();
    }
    public void resetBars() {
    	data=input.getData();
		if (!(data==null||data.length==0)) {
			this.bars=create_bars();}
    }
    public void inputButtonInitialize() {
    	input_button.setOnAction(e->{
        	String inputText = input_textfield.getText();
        	try {
        		input.inputData(inputText);
        		this.bars=create_bars();
        		}
        	catch(InvalidInputException err) {
        		err.showMessage();
        	}
        });
    }
    public void sortButtonInitialize() {
    	sort_button.setOnAction(e->{
    		try { letSort();}
    		catch(MissingInputException err) {
    			err.showMessage();
    		}
    	});
    }
    public void letSort() throws MissingInputException {
        if(bars==null||bars.length==0){
            throw new MissingInputException();
        }
        else{
        	resetBars();
        	SequentialTransition sortingAnimation=current_sort.sort(bars);
        	sortingAnimation.rateProperty().bind(speed_slider.valueProperty());
        	sortingAnimation.play();
        	/*sort_button.setDisable(true);
        	sortingAnimation.setOnFinished(ee-> {
        		 sort_button.setDisable(false);  // Re-enable button after sorting finishes
        	});*/
        }
    }
    public void speedSliderInitialize() {
    	float default_speed=2; //Bar.getSpeed();
    	speed_slider.setMin(0.5);
        speed_slider.setMax(30.0);
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
    	if (algo_name=="Insertion Sort") {current_sort=new InsertionSort();}
		else if (algo_name=="Bubble Sort") {current_sort=new BubbleSort();}
		else {current_sort=new QuickSort();}
    }
    public Bar[] create_bars() {
    	BarsCreator collection=new BarsCreator(input.getData());
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