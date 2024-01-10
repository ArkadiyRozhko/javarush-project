package com.javarush.task.task25.task2515;

public class SpaceShip extends BaseObject{
       private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };
    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }
    private double dx=0;

    public void moveLeft(){
        dx=-1;
    }
    public void moveRight(){
        dx=1;
    }

    public void move(){
        setX(getX()+dx);
        checkBorders(getRadius(), Space.game.getWidth() - getRadius() + 1, 1, Space.game.getHeight() + 1);
    }

    public void draw(Canvas canvas){
        canvas.drawMatrix(getX() - getRadius() + 1, getY() - getRadius() + 1, matrix, 'M');
    }

    public void fire(){
        Space.game.getRockets().add(new Rocket(getX() - 2, getY()));
        Space.game.getRockets().add(new Rocket(getX() + 2, getY()));
    }
}
