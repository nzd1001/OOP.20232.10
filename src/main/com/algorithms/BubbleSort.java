package main.com.algorithms;
import java.util.ArrayList;
import java.util.List;
import main.com.components.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public class BubbleSort extends Sort{
    @Override
    public ArrayList<Transition> sort(Bar[] bars){
    	ArrayList<Transition> t=new ArrayList<>();
        int i, j;
        int n=bars.length;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                t.add(this.reColor(SELECT_COLOR,bars[j],bars[j+1]));
                if (bars[j].getValue() > bars[j + 1].getValue()) {
                    t.add(swap(bars,j,j+1));
                    swapped=true;
                    // Swap bars[j].getValue() and bars[j+1]
                    
                }
                t.add(this.reColor(INITIAL_COLOR,bars[j],bars[j+1]));
            }
            t.add(bars[j].changeColor(SORTED_COLOR));
            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
        t.add(reColor(SORTED_COLOR, bars));
        return t;
    }
}