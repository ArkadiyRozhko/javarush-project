package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        Map<Character,Integer> digitMap=initMap();
        char[] list= s.toCharArray();
        int result=0;
        for (int i = list.length-1;i>=0;i--){

            if (i!=0&&digitMap.get(list[i])>digitMap.get(list[i-1])) {
               result+=digitMap.get(list[i])-digitMap.get(list[i-1]);
               i-=1;
            }else {
                result += digitMap.get(list[i]);
            }
        }
        return result;
    }

    private static Map<Character,Integer> initMap(){
        Map<Character,Integer> digit=new HashMap();
        digit.put('I',1);
        digit.put('V',5);
        digit.put('X',10);
        digit.put('L',50);
        digit.put('C',100);
        digit.put('D',500);
        digit.put('M',1000);
        return digit;
    }
}
