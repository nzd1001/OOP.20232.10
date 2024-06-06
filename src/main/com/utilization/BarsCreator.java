package main.com.utilization;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import main.com.components.Bar;
public class BarsCreator {
    private int[] data=new int[100];
    public BarsCreator(int[] data){
        this.data=data;
    }
    public Bar[] create(Region container){
    	int l=data.length;
    	double unit_w=Math.min(25, (int)(container.getWidth()/l));
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
