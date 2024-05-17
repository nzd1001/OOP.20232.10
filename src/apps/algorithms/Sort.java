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
    final Color INITIAL_COLOR = Color.CYAN;
    final Color SELECT_COLOR = Color.GREENYELLOW;
    final Color SORTED_COLOR = Color.PURPLE; 
    ParallelTransition reColor(Bar[] bars,Color[] colors){
        ParallelTransition t=new ParallelTransition();
        for (int i=0;i<bars.length;i++){
            bars[i].changeColor(colors[i]);
        }
        return t;
    }
    ParallelTransition reColor(Color[] colors,Bar... bars){
        ParallelTransition t=new ParallelTransition();
        for (int i=0;i<bars.length;i++){
            t.getChildren().add(bars[i].changeColor(colors[i]));
        }
        return t;
    }
    ParallelTransition reColor(Color color, Bar... bars){
        ParallelTransition t=new ParallelTransition();
        for (int i=0;i<bars.length;i++){
            t.getChildren().add(bars[i].changeColor(color));
        }
        return t;
    }
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

  