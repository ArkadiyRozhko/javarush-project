package com.javarush.task.task25.task2515;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Space {
    private int width;
    private int height;
    private SpaceShip ship;
    private List<Ufo> ufos=new ArrayList<>();
    private List<Rocket>rockets=new ArrayList<>();
    private List<Bomb>bombs=new ArrayList<>();
    public static Space game;

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public List<Ufo> getUfos() {
        return ufos;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public static void main(String[] args) {
        game = new Space(50, 20);
        game.setShip(new SpaceShip(10, 18));
        game.run();
    }
    public void run(){
        Canvas canvas = new Canvas(width, height);

        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //Игра работает, пока корабль жив
        while (ship.isAlive()) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если "стрелка влево" - сдвинуть фигурку влево
                System.out.print(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.moveLeft();
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.moveRight();
                    //Если "пробел" - запускаем шарик
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ship.fire();
            }

            //двигаем все объекты игры
            moveAllItems();

            //проверяем столкновения
            checkBombs();
            checkRockets();
            //удаляем умершие объекты из списков
            removeDead();

            //Создаем НЛО (1 раз в 10 ходов)
            createUfo();

            //Отрисовываем все объекты на холст, а холст выводим на экран
            canvas.clear();
            draw(canvas);
            canvas.print();

            //Пауза 300 миллисекунд
            Space.sleep(400);
        }

        //Выводим сообщение "Game Over"
        System.out.println("Game Over!");
    }
    public void draw(Canvas canvas){
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }

        for (BaseObject object : getAllItems()) {
            object.draw(canvas);
        }
    }
    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
        }
    }
    public List<BaseObject> getAllItems() {
        ArrayList<BaseObject> list = new ArrayList<>(ufos);
        list.add(ship);
        list.addAll(bombs);
        list.addAll(rockets);
        return list;
    }
    public void moveAllItems() {
        for (BaseObject object : getAllItems()) {
            object.move();
        }
    }
    public void createUfo() {
        if (ufos.size() > 0) return;

        int random10 = (int) (Math.random() * 10);
        if (random10 == 0) {
            double x = Math.random() * width;
            double y = Math.random() * height / 2;
            ufos.add(new Ufo(x, y));
        }
    }
    public void checkBombs() {
        for (Bomb bomb : bombs) {
            if (ship.isIntersect(bomb)) {
                ship.die();
                bomb.die();
            }

            if (bomb.getY() >= height)
                bomb.die();
        }
    }
    public void checkRockets() {
        for (Rocket rocket : rockets) {
            for (Ufo ufo : ufos) {
                if (ufo.isIntersect(rocket)) {
                    ufo.die();
                    rocket.die();
                }
            }

            if (rocket.getY() <= 0)
                rocket.die();
        }
    }
    public void removeDead() {
        for (BaseObject object : new ArrayList<BaseObject>(bombs)) {
            if (!object.isAlive())
                bombs.remove(object);
        }

        for (BaseObject object : new ArrayList<BaseObject>(rockets)) {
            if (!object.isAlive())
                rockets.remove(object);
        }

        for (BaseObject object : new ArrayList<BaseObject>(ufos)) {
            if (!object.isAlive())
                ufos.remove(object);
        }
    }

}
