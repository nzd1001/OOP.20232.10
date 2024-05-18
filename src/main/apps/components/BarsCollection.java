package main.apps.components;

import javafx.scene.paint.Color;
public class BarsCollection {
    private int[] data=new int[100];
    public BarsCollection(int[] data){
        this.data=data;
    }
    public Bar[] initialize(){
        Bar[] bars=new Bar[data.length];
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar(data[i]);
            bars[i].setScaleY(-1.0);
            bars[i].setX(i*25);
            bars[i].setFill(Color.CYAN);
            bars[i].setStroke(Color.BLACK);
            bars[i].setWidth((25));
            bars[i].setHeight(5*data[i]);
          }
        return bars;
    }
}
