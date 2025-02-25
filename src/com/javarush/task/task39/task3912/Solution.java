package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                { 1, 1, 1, 1, 0, 1, 0, 0, 1, 1 },
                { 1, 0, 1, 1, 0, 1, 0, 0, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 0, 0, 1, 0, 1, 1, 1 }
        };
        System.out.println(maxSquare(matrix));

    }

    public static int maxSquare(int[][] matrix) {
        int max=0;
        int dp[][]=new int[matrix.length+1][matrix[0].length+1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    int up=dp[i][j+1];
                    int left=dp[i+1][j];
                    int diagonal= dp[i][j];
                    dp[i+1][j+1]=Math.min(Math.min(up,left),diagonal)+1;
                    max=Math.max(max,dp[i+1][j+1]);
                }
            }
        }
        return max*max;

    }
}
