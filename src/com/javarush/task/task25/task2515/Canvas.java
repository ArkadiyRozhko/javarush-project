package com.javarush.task.task25.task2515;

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix=new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setPoint(double x,double y, char c){
        int roundX= (int) Math.round(x);
        int roundY= (int) Math.round(y);
        if ((roundX>=0&&roundX<matrix[0].length)&&(roundY>=0&&roundY< matrix.length)) {
            matrix[roundY][roundX]=c;
        }
    }
    public void drawMatrix(double x, double y, int[][] matrix, char c){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                if (matrix[i][j] != 0) {
                    setPoint(x+j,y+i,c);
                }
            }
        }
    }

    public void clear(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j]=' ';
            }
        }
    }

    public void print(){
        for (int i = 0; i< matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
}
