package com.javarush.task.task34.task3410.model;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

abstract public class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        boolean b;
        int x1 = getX();
        int y1 = getY();
        switch (direction) {
            case UP:
                y1 -= FIELD_CELL_SIZE;
                break;
            case DOWN:
                y1 += FIELD_CELL_SIZE;
                break;
            case LEFT:
                x1 -= FIELD_CELL_SIZE;
                break;
            case RIGHT:
                x1 += FIELD_CELL_SIZE;
        }
        if (x1 == gameObject.getX() && y1 == gameObject.getY()) b = true;
        else b = false;
        return b;
    }
}
