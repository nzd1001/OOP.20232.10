package not_important;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AlgorithmVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create algorithm selection 
        Label algoLable= new Label("Choose the algorithm that you interested in:"); 
        algoLable.setStyle("-fx-font-size: 15px;");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
        ChoiceBox<String> selectionBox = new ChoiceBox<String>();
        selectionBox.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort");
        selectionBox.getSelectionModel().selectFirst(); 
        selectionBox.setStyle("-fx-font-size: 14px;");
        // Create Start button
        Button startButton=new Button("Start");
        startButton.setStyle("-fx-font-size: 20px;");
        // Create Quit button
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(event -> confirmQuit(primaryStage));
        quitButton.setStyle("-fx-font-size: 15px;"); 
        // Create Help button
        Button helpButton = new Button("Help");
        helpButton.setOnAction(event -> showHelpScene());
        helpButton.setStyle("-fx-font-size: 15px;"); 
        //GridPane for algorithm selection
        GridPane gridPane=new GridPane();
        gridPane.setMinSize(400, 200); 
       
      //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(10); 
        gridPane.setHgap(5);       
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(algoLable,0,0);
        gridPane.add(selectionBox,1,0);
        gridPane.add(startButton,0,2);
        // HBox for Quit and Help buttons
        HBox helpQuitBox = new HBox(14);
        helpQuitBox.setAlignment(Pos.BOTTOM_RIGHT); 
        helpQuitBox.getChildren().addAll(quitButton, helpButton);

        // Main VBox layout
        BorderPane root = new BorderPane();
        root.setCenter(gridPane);
        root.setBottom(helpQuitBox);

        // Set up Scene and Stage
        Scene mainMenu = new Scene(root, 1000, 600);
        primaryStage.setTitle("Algorithm Visualizer");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    private void confirmQuit(Stage stage) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Exit Confirmation");
        confirmation.setContentText("Are you sure you want to quit?");
        confirmation.showAndWait();
        if (confirmation.getResult() == ButtonType.OK) {
            stage.close();
        }
    }

    private void showHelpScene() {
        // Create a new Stage and Scene for Help 
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
