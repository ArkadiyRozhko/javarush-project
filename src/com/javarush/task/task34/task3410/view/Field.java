package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;
import com.javarush.task.task34.task3410.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    View view;
    EventListener eventListener;
    public Field(View view){
        this.view=view;
        KeyHandler keyHandler=new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        for (GameObject object:view.getGameObjects().getAll()
             ) {
            object.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode=e.getKeyCode();
            if (keycode==KeyEvent.VK_LEFT) {
                eventListener.move(Direction.LEFT);
            }else if (keycode == KeyEvent.VK_RIGHT) {
                eventListener.move(Direction.RIGHT);
            } else if (keycode==KeyEvent.VK_UP) {
                eventListener.move(Direction.UP);
            } else if (keycode==KeyEvent.VK_DOWN) {
                eventListener.move(Direction.DOWN);
            } else if (keycode==KeyEvent.VK_R) {
                eventListener.restart();
            }
        }
    }
}
