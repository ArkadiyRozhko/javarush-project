package com.javarush.task.task34.task3410.model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class LevelLoader {
    private Path levels;
    public LevelLoader(Path levels) {
        this.levels=levels;
    }
    public GameObjects getLevel(int level){
        int roundLevel=level%60==0?60:level%60;
        Map<Integer,char[][]>map=new HashMap<>();
        try (BufferedReader reader=Files.newBufferedReader(levels)){
            String string;
            Integer maze=0;
            int sizeX=0;
            int sizeY=0;
            int count=0;
            char[][]pole=new char[sizeY][sizeX];
            while ((string=reader.readLine())!=null){
                if (string.isEmpty()){
                    continue;
                }
                if (string.startsWith("Maze:")) {
                    String[]mazeValue=string.split(":");
                    maze=Integer.parseInt(mazeValue[1].replaceAll(" ",""));
                    sizeX=0;
                    sizeY=0;
                    count=0;
                }
                if (string.startsWith("Size X:")) {
                    String[]xValue=string.split(":");
                    sizeX=Integer.parseInt(xValue[1].replaceAll(" ",""));
                }
                if (string.startsWith("Size Y:")) {
                    String[]yValue=string.split(":");
                    sizeY=Integer.parseInt(yValue[1].replaceAll(" ",""));
                }
                if (string.startsWith("End")) {
                    pole=new char[sizeY][sizeX];
                }
                if (sizeY !=0&&sizeX!=0) {
                    if (string.length() == sizeX) {
                        pole[count]=string.toCharArray();
                        count+=1;
                    }
                    if (count == sizeY&!string.startsWith("******")) {
                        map.put(maze,pole);
                    }
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Player player=new Player(0,0);
        Set<Wall> walls = new HashSet<Wall>();
        Set<Box> boxes = new HashSet<Box>();
        Set<Home> homes = new HashSet<Home>();

        char[][]levelMap=map.get(roundLevel);

        int y0=Model.FIELD_CELL_SIZE/2;
        for (int y = 0; y < levelMap.length; y++) {
            int x0=Model.FIELD_CELL_SIZE/2;
            for (int x = 0; x < levelMap[y].length; x++) {

                switch (levelMap[y][x]){
                    case 'X':
                        walls.add(new Wall(x0,y0));
                        break;
                    case '*':
                        boxes.add(new Box(x0,y0));
                        break;
                    case '.':
                        homes.add(new Home(x0,y0));
                        break;
                    case '&':
                        boxes.add(new Box(x0,y0));
                        homes.add(new Home(x0,y0));
                        break;
                    case '@':
                        player=new Player(x0,y0);
                        break;
                }
                x0+=Model.FIELD_CELL_SIZE;
            }
            y0+=Model.FIELD_CELL_SIZE;
        }

        GameObjects objects = new GameObjects(walls, boxes, homes, player);
        return objects;

    }
}
