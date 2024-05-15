package not_important;
public class Bird{
    public int speed;
    public Bird(int speed){
        this.speed=speed;
    }
    public static void swap(Bird...birds){
        Bird temp=birds[0];
        birds[0]=birds[1];
        birds[1]=temp;
    }
    public static void main(String[] args){
        Bird[] birds=new Bird[2];
        birds[0]=new Bird(3);
        Bird temp=birds[0];
        birds[1]=new Bird(5);
        swap(birds);
        System.out.println(temp.speed);
        System.out.println(birds[0].speed+" and "+birds[1].speed);
    }
}