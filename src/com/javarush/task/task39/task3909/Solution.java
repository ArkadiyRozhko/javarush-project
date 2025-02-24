package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

public class Solution {
    public static void main(String[] args) {

        System.out.println(isOneEditAway("", ""));

    }

    public static boolean isOneEditAway(String first, String second) {
        int a,b,c;
        int d[][]=new int[first.length()+1][second.length()+1];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                if (j == 0&&i==0) {
                    d[i][j]=0;
                }else if (i == 0&&j!=0) {
                    d[i][j]=j;
                } else if (j==0&&i!=0) {
                    d[i][j]=i;
                }else {
                    char one=first.charAt(i-1);
                    char two=second.charAt(j-1);
                    if (first.charAt(i-1)==second.charAt(j-1)) {
                        d[i][j]=d[i-1][j-1]+0;
                    }else {
                        a=d[i][j-1]+1;
                        b=d[i-1][j]+1;
                        c=d[i-1][j-1]+1;
                        d[i][j]=Math.min(Math.min(a,b),Math.min(b,c));
                    }
                }
            }
        }

        if (first.equals(second)||d[first.length()][second.length()] == 1) {
            return true;
        }else {
            return false;
        }

    }
}
