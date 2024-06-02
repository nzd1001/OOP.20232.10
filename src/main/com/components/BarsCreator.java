package main.com.components;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
public class BarsCreator {
    private int[] data=new int[100];
    public BarsCreator(int[] data){
        this.data=data;
    }
    public Bar[] initialize(Region container){
    	int l=data.length;
    	double unit_w=Math.min(22, (int)(container.getWidth()/l));
    	double unit_h=((container.getHeight()-40)/50);
        Bar[] bars=new Bar[l];
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar(data[i]);
            bars[i].setY(-unit_h*data[i]);
            bars[i].setX(i*unit_w);
            bars[i].setFill(Color.CYAN);
            bars[i].setStroke(Color.BLACK);
            bars[i].setWidth((unit_w));
            bars[i].setHeight(unit_h*data[i]);
          }
        return bars;
    }
}
