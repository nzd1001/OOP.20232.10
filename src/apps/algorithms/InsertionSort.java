package apps.algorithms;
import java.util.ArrayList;
import java.util.List;

import apps.components.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public class InsertionSort extends Sort{
    @Override
    public void sort(Bar[] bars){
      SequentialTransition t=new SequentialTransition();
      for (int i = 1; i < bars.length; i++) {
        int keyVal = bars[i].getValue();
        int j = i - 1;
        t.getChildren().add(bars[i].changeColor(Color.RED));
        while (j >=0 && bars[j].getValue() >keyVal) {
          t.getChildren().add(bars[j].changeColor(Color.GREEN));
          t.getChildren().add(swap(bars,j,j+1));
          t.getChildren().add(bars[j+1].changeColor(Color.CYAN));
          j--;
        }
        t.getChildren().add(bars[j+1].changeColor(Color.CYAN));
        }
      t.play();
    }
}
