package main.apps.algorithms;
import java.util.List;

import main.apps.components.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.animation.SequentialTransition;

public class QuickSort extends Sort {
	
	public void sort(Bar[] bars, int low, int high, SequentialTransition t) {
		if(low<=high) {
			if(low==high) {
				t.getChildren().add(bars[low].changeColor(SORTED_COLOR));
			}
			else {
				int checkk = partition(bars, low, high, t);
				sort(bars, low, checkk-1, t);
				sort(bars, checkk+1, high, t);}
		}
	}
	public void sort(Bar[] bars) {
		SequentialTransition t=new SequentialTransition();
		sort(bars, 0, bars.length-1,t);
		t.play();
	}
	public int partition(Bar[] bars, int low, int high, SequentialTransition t) {
		int pivot = low;
		t.getChildren().add(bars[pivot].changeColor(PIVOT_COLOR));
		int i = low;
		for (int j = low+1; j <= high; j++) {
			t.getChildren().add(bars[j].changeColor(SELECT_COLOR));
			if (bars[j].getValue() < bars[pivot].getValue()) {
				i++;
				t.getChildren().add(bars[i].changeColor(KEY_COLOR));
				if(i-1!=pivot) {
				t.getChildren().add(bars[i-1].changeColor(INITIAL_COLOR));
			}
				t.getChildren().add(swap(bars,i,j));
				t.getChildren().add(bars[i].changeColor(KEY_COLOR));
			}
			t.getChildren().add(bars[j].changeColor(INITIAL_COLOR));
		}
		t.getChildren().add(swap(bars,pivot,i));
		t.getChildren().add(bars[pivot].changeColor(INITIAL_COLOR));
		t.getChildren().add(bars[i].changeColor(SORTED_COLOR));
		return i;
	}
	
}