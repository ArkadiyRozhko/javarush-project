package com.javarush.task.task25.task2515;

public class Rocket extends BaseObject{

    public Rocket(double x, double y) {
        super(x, y, 1);
    }
    public void move() {
        setY(getY()-1);
    }
    public void draw (Canvas canvas){
        canvas.setPoint(getX(),getY(),'R');
    }

}
