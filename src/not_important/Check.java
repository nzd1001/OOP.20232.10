import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Check extends Application {

    private final Rectangle rect = new Rectangle(100, 50, Color.RED);
    private final FadeTransition fadeOut = new FadeTransition(Duration.millis(500), rect);
    private final FadeTransition fadeIn = new FadeTransition(Duration.millis(500), rect);
    private final PauseTransition pause = new PauseTransition(Duration.millis(1000));
    private final SequentialTransition fullTransition = new SequentialTransition(fadeOut, pause, fadeIn);
    private int currentStep = 0;
    private boolean isPlaying = false;

    @Override
    public void start(Stage primaryStage) {
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> playNextStep());

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(event -> stopPlayback());

        Group root = new Group(rect, nextButton, stopButton);
        rect.setTranslateX(100);
        rect.setTranslateY(100);
        nextButton.setTranslateX(250);
        nextButton.setTranslateY(100);
        stopButton.setTranslateX(350);
        stopButton.setTranslateY(100);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Step-by-Step Transition");
        primaryStage.show();
    }

    private void playNextStep() {
        if (!isPlaying) {
            isPlaying = true;
            playFromCurrentStep();
        } else {
            fullTransition.pause();
            playFromCurrentStep();
        }
    }

    private void playFromCurrentStep() {
        fullTransition.playFrom(Duration.ZERO);
        currentStep++;
    }

    private void stopPlayback() {
        isPlaying = false;
        fullTransition.pause();
    }
    public static void main(String[] args) {
        launch(args);
    }
}