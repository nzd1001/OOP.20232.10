package main.com.algorithms;
import java.util.ArrayList;
import java.util.List;
import main.com.components.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public class InsertionSort extends Sort{
    @Override
    public ArrayList<Transition> sort(Bar[] bars){
      ArrayList<Transition> t=new ArrayList<>();
	  for (int i = 1; i < bars.length; i++) {
	    int keyVal = bars[i].getValue();
	    int j = i - 1;
	    t.add(bars[i].changeColor(KEY_COLOR));
	    while (j >=0 && bars[j].getValue() >keyVal) {
	      t.add(bars[j].changeColor(SELECT_COLOR));
	      t.add(swap(bars,j,j+1));
	      t.add(bars[j+1].changeColor(INITIAL_COLOR));
	      j--;
	    }
	    t.add(bars[j+1].changeColor(INITIAL_COLOR));
	    }
	  t.add(reColor(SORTED_COLOR, bars));
	  return t;
    }
}
