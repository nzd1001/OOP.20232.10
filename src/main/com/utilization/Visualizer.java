package main.com.utilization;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import main.com.algorithms.*;
import main.com.components.Bar;
public class Visualizer {
	private DataHandler handler;
	private Sort algo;
	private Bar[] bars;

	public Visualizer(DataHandler handler) {
		this.handler=handler;
	}
	public Bar[] getBars() {
		return bars;
	}
	
	public Sort getAlgo() {
		return algo;
	}
	public void setAlgo(Sort algo) {
		this.algo = algo;
	}
	public DataHandler getDataHandler() {
		return handler;
	}
	
	public void create_bars(Region container){
		int[] data=handler.getData();
		int l=data.length;
		double bar_unit_w=Math.min(25,(int)((container.getWidth()-70)/l));
		double bar_unit_h=((container.getHeight()-40)/50);
	    bars=new Bar[l];
	    for (int i = 0; i < bars.length; i++) {
	        bars[i] = new Bar(data[i]);
	        bars[i].setY(-bar_unit_h*data[i]);
	        bars[i].setX(i*bar_unit_w);
	        bars[i].setFill(Color.rgb(184,255,255));
	        bars[i].setStroke(Color.BLACK);
	        bars[i].setWidth(bar_unit_w);
	        bars[i].setHeight(bar_unit_h*data[i]);
	      }    
	 }
	 public SequentialTransition sort() {
		 SequentialTransition animation=algo.sort(bars);
		 return animation;
	 }
	 
}
