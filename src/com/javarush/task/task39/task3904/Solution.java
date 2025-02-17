package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/

public class Solution {
    private static int n = 1000;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) {
            return 0;
        } else {
            long dp[] = new long[n + 1];

            for (int i = 0; i < dp.length; i++) {
                if (i == 0 || i == 1) {
                    dp[i] = 1;
                } else if (i == 2) {
                    dp[i] = 2;
                } else if (i == 3) {
                    dp[i] = 4;
                } else {
                    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
                }
            }
            return dp[n];
        }
    }

}

