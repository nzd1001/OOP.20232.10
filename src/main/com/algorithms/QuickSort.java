package main.com.algorithms;
import java.util.ArrayList;
import java.util.List;
import main.com.components.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.animation.SequentialTransition;

public class QuickSort extends Sort {
	
	public void sort(Bar[] bars, int low, int high, ArrayList<Transition> t) {
		if(low<=high) {
			if(low==high) {
				t.add(bars[low].changeColor(SORTED_COLOR));
			}
			else {
				int checkk = partition(bars, low, high, t);
				sort(bars, low, checkk-1, t);
				sort(bars, checkk+1, high, t);}
		}
	}
	public ArrayList<Transition> sort(Bar[] bars) {
		ArrayList<Transition> t=new ArrayList<>();
		sort(bars, 0, bars.length-1,t);
		return t;
	}
	public int partition(Bar[] bars, int low, int high, ArrayList<Transition> t) {
		int pivot = low;
		t.add(bars[pivot].changeColor(PIVOT_COLOR));
		int i = low;
		for (int j = low+1; j <= high; j++) {
			t.add(bars[j].changeColor(SELECT_COLOR));
			if (bars[j].getValue() < bars[pivot].getValue()) {
				i++;
				t.add(bars[i].changeColor(KEY_COLOR));
				if(i-1!=pivot) {
				t.add(bars[i-1].changeColor(INITIAL_COLOR));
			}
				t.add(swap(bars,i,j));
				t.add(bars[i].changeColor(KEY_COLOR));
			}
			t.add(bars[j].changeColor(INITIAL_COLOR));
		}
		t.add(swap(bars,pivot,i));
		t.add(bars[pivot].changeColor(INITIAL_COLOR));
		t.add(bars[i].changeColor(SORTED_COLOR));
		return i;
	}
	
}