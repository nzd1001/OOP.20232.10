package apps.algorithms;
import apps.components.Bar;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public abstract class Sort {
    final static int d=25;
    final Color START_COLOR = Color.CYAN;
    final Color SELECT_COLOR = Color.GREENYELLOW;
    final Color SORTED_COLOR = Color.PURPLE; 
    ParallelTransition swap(Bar[] bars,int i,int j){
        //swap 2 bars given their indexes
        ParallelTransition t=new ParallelTransition();
        int moveDistance=d*(j-i);
        t.getChildren().addAll(bars[i].move(moveDistance),bars[j].move(-moveDistance));
        //This swap one is notable
        Bar temp=bars[i];
        bars[i]=bars[j];
        bars[j]=temp;
        return t;
    }
    public abstract void sort(Bar[] bars);
}
