package main.com.components;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;
public class Bar extends Rectangle{
    private int value;

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
        t.setDuration(Duration.millis(250));
        return t;
    }
    public TranslateTransition move(double x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(300));
        t.setByX(x);
        return t;
      }
}
