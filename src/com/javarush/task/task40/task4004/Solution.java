package com.javarush.task.task40.task4004;

import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        //напишите тут ваш код
        int intersections = 0;
        for (int i = 0; i < polygon.size(); i++) {
            Point p1 = polygon.get(i);
            Point p2 = polygon.get((i + 1) % polygon.size());

            // Перевірка перетину променя з ребром
            if (p1.y != p2.y) {
                if ((point.y > Math.min(p1.y, p2.y)) && (point.y <= Math.max(p1.y, p2.y))) {
                    double xIntersection = (double) (point.y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;
                    if (point.x < xIntersection) {
                        intersections++;
                    }
                }
            }
        }
        return (intersections % 2 != 0);
    }

}

