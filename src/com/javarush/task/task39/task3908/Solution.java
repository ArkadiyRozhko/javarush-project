package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        String s ="oooggggtts";
        System.out.println(isPalindromePermutation(s));

    }

    public static boolean isPalindromePermutation(String s) {
        Map<Character,Integer> count=new HashMap<>();
        for (Character c:s.toLowerCase().toCharArray()
             ) {
            if (count.containsKey(c)) {
                Integer temp=count.get(c);
                count.put(c,temp+1);
            }else {
                count.put(c,1);
            }
        }
        int divide2=0;

        for (Integer i: count.values()
             ) {
            if (i%2 != 0) {
                divide2++;
            }
        }
        if (divide2 >1) {
            return false;
        }
        return true;
    }
}
