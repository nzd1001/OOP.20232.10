package not_important;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneSwitcher extends Application {

    private Scene scene1;
    private Scene scene2;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        // Create Scene 1 content (replace with your actual content)
        Label scene1Label = new Label("This is Scene 1");
        scene1 = new Scene(scene1Label, 200, 100);

        // Create Scene 2 content (replace with your actual content)
        Label scene2Label = new Label("This is Scene 2");
        scene2 = new Scene(scene2Label, 300, 150);

        // Set the initial scene
        stage.setScene(scene1);
        stage.setTitle("Scene Switcher");
        stage.show();

        // Button to switch to Scene 2 (replace with your actual button)
        Button switchButton = new Button("Go to Scene 2");
        switchButton.setOnAction(event -> stage.setScene(scene2));

        // Add the button to Scene 1 (replace with your layout)
        scene1Label.setGraphic(switchButton);
    }

    public static void main(String[] args) {
        launch(args);
    }
}