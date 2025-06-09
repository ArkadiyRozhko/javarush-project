package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException {
        Horse horse1=new Horse("Сизий",3,0);
        Horse horse2=new Horse("Бистрий",3,0);
        Horse horse3=new Horse("Булат",3,0);
        List<Horse>horses1=new ArrayList<>();
        horses1.add(horse1);
        horses1.add(horse2);
        horses1.add(horse3);
        game=new Hippodrome(horses1);
        game.run();
        game.printWinner();

    }

    public void run() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void move(){
        for (Horse horse:horses
             ) {
            horse.move();
        }
    }
    public void print(){
        for (Horse horse:horses
             ) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner(){
        Horse winner=horses.get(0);
        for (Horse horse:horses
             ) {
            if (winner.distance < horse.distance) {
                winner=horse;
            }
        }
        return winner;
    }
    public void printWinner(){
        System.out.println("Winner is "+getWinner().name+"!");
    }
}
