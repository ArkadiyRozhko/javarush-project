package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {

        if ((number &(1L<<i))==(1L<<i)) { //перевіряєм чи по індексу і в нас знаходиться 1
            if ((number &(1L<<j))!=(1L<<j)) { //перевіряєм чи по індексу j в нас знаходиться 0
                number=(number|(1L<<j));
                number=(number&~(1L<<i));
            }
        } else if ((number &(1L<<i))!=(1L<<i)) { //перевіряєм чи по індексу і в нас знаходиться 0
            if ((number & (1L << j)) == (1L << j)) { //перевіряєм чи по індексу j в нас знаходиться 1
                number = (number | (1L << i));
                number = (number & ~(1L << j));
            }
        }

        return number;
    }
}
