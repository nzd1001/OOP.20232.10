package main.apps.components;
import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public class Bar extends Rectangle{
    private int value;
    private static int speed=400;
    public static int getSpeed() {
    	return speed;
    }
    public static void setSpeed(int s) {
    	speed=s;
    }
    public Bar(int value){
        this.value=value;
    }
    public int getValue(){
        return this.value;
    }
    public void setValue(int new_val){
        this.value=new_val;
    }
    public FillTransition changeColor(Color color){
        FillTransition t= new FillTransition();
        t.setShape(this);
        t.setToValue(color);
        t.setDuration(Duration.millis(speed));
        return t;
    }
    public TranslateTransition move(int x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(speed));
        t.setByX(x);
        return t;
      }
    
}
