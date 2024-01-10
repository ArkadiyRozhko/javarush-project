package com.javarush.task.task25.task2515;

public class Ufo extends BaseObject{
        private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };
    public Ufo(double x, double y) {
        super(x, y, 3);
    }
    public void draw(Canvas canvas){
        canvas.drawMatrix(getX() - getRadius() + 1, getY() - getRadius() + 1, matrix, 'U');
    }
    public void move(){
        double dx = Math.random() * 2 - 1;
        double dy = Math.random() * 2 - 1;

        setX(getX()+dx);
        setY(getY()+dy);

        checkBorders(getRadius(), Space.game.getWidth() - getRadius() + 1, getRadius() - 1, Space.game.getHeight() / 2 - 1);

        int random10 = (int) (Math.random() * 10);
        if (random10 == 0)
            fire();
    }
    public void fire(){
        Space.game.getBombs().add(new Bomb(getX(), getY() + 3));

    }
}
