package main.apps.algorithms;
import main.apps.components.Bar;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public abstract class Sort {
    final Color INITIAL_COLOR = Color.CYAN;
    final Color KEY_COLOR = Color.ORANGE;
    final Color PIVOT_COLOR = Color.LIGHTSLATEGRAY;
    final Color SELECT_COLOR = Color.GREENYELLOW;
    final Color SORTED_COLOR = Color.PINK; 
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
    	double d=bars[0].getWidth();
        ParallelTransition t=new ParallelTransition();
        double moveDistance=d*(j-i);
        t.getChildren().addAll(bars[i].move(moveDistance),bars[j].move(-moveDistance));
        //This swap one is notable
        Bar temp=bars[i];
        bars[i]=bars[j];
        bars[j]=temp;
        return t;
    }
    public abstract SequentialTransition sort(Bar[] bars);
}

  