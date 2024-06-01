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
import main.apps.components.*;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.util.*;
public class visualizer_scene_controller {
	@FXML private Button ok_button = new Button();
	@FXML private TextField input_textfield = new TextField();
    @FXML private Button sort_button=new Button();
    @FXML private Pane displaySort=new Pane();
    @FXML private Button randomize_button=new Button();
    @FXML private Button back_button=new Button();
    @FXML private Label speed_label=new Label();
    @FXML private Slider speed_slider=new Slider();
    @FXML private Button reset_button=new Button();
    @FXML private ChoiceBox<String> algo_box=new ChoiceBox();
    private static int[] data=new int[7];
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Bar[] bars;
    private Sort current_sort;
    private boolean isValidInput = true;
    private String[] algo_list= {"Insertion Sort","Bubble Sort","Quick Sort"};
    //private int length=1;
    //private int[] intArray = new int[length];
    //speed_label.setText("Speed: 400");
    //speed_label.textProperty().bind(slider.valueProperty().asString("Speed: %.0f");
    public void initialize() throws IOException{
        //int[] data={10,6,57,82,41,35,19};
    	mapSort(algo_list[main_menu_controller.getSortIndex()]);
    	randomize_button.setOnAction(e->{this.bars=create_random_bars();});
        sort_button.setOnAction(ee->{
            if(bars==null||bars.length==0){
                showInputDataAlert();
            }
            else{
                current_sort.sort(bars);
            }
            
        });
        reset_button.setOnAction(epl->{
        	//int[] dataa=data;
        	this.bars=create_bars(data);
        });
        back_button.setOnAction(e->{
            try{
                switch_scene1(e);}
            catch(IOException err){
                System.err.println("Error!");
            }});
        ok_button.setOnAction(eee->{
        	data = inputTextField();
        	if(isValidInput) {
        		this.bars=create_bars(data);}
        	else {
        		showAlert();
        	}
        });
        speedSliderInitialize();
        choiceBoxInitialize();
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
    	algo_box.getSelectionModel().select(algo_list[main_menu_controller.getSortIndex()]);
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
    public int[] inputTextField() {
    	String inputText = input_textfield.getText();
    	//System.out.println(inputText);
        String[] inputArray = inputText.split(",");
        //System.out.println(inputArray[0]+inputArray[1]+inputArray[2]);
        //length = inputArray.length;
        int[] intArray = new int[inputArray.length];
        //boolean isValidInput = true;
        for (int jj = 0; jj<inputArray.length; jj++) {
            try {
                String xyz = inputArray[jj].trim(); // Trim leading/trailing whitespace
            	//for (int i = 0; i < inputArray.length; i++) {
                intArray[jj] = Integer.parseInt(xyz);
                //return intArray;
            	}
             catch (NumberFormatException e) {
                isValidInput = false;
                break;
            }
        //for (int kkk : intArray) System.out.println(kkk+" ");
        }
        return intArray;
    }
    public Bar[] create_bars(int[] intArray) {
    	BarsCollection collection=new BarsCollection(intArray);
        Bar[] bars=collection.initialize();
        displaySort.getChildren().clear();
        displaySort.getChildren().addAll(Arrays.asList(bars));
        return bars;
    }
    public Bar[] create_random_bars(){
        Random random = new Random();
        int dodaingaunhien = random.nextInt(18)+3;
        data=new int[dodaingaunhien];
        for (int i = 0; i < data.length; i++) {
            
            data[i] = random.nextInt(50)+1;
          }
       return create_bars(data);
    }
    public void switch_scene1(ActionEvent event) throws IOException{
        //FXMLLoader loader1 = new FXMLLoader(getClass().getResource("resources/view/main_menu.fxml")); 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/main_menu.fxml")); 
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
    public void showAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Please enter only comma-separated numbers.");
		alert.setContentText("The input you provided contains non-numeric characters.");
		alert.showAndWait();
	}

}