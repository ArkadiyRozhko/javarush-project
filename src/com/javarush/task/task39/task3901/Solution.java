package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
//        int max=0;
//        int start=0;
//        int end=1;
//        int startIndex=0;
//        int endIndex=0;
//        if (s == null||s.length()==0) {
//           max=0;
////        } else if (s.length()==1) {
////            max=1;
//        }else {
//            while (start<s.length()){
//                end=start+1;
//                while (end<s.length()){
//
//                    if (s.charAt(start) == s.charAt(end)) {
//                        endIndex=end;
//                        String temp=s.substring(startIndex,endIndex);
//                        max=max>temp.length()?max:temp.length();
//                        start=startIndex=end;
//                        end++;
//                        continue;
//                    }
//                    end++;
//                }
//                start++;
//
//            }
//        }
//
//        return max;
//    }
        if (s == null) {
            return 0;
        }
        int maxLength = 0;
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (chars.size() > maxLength) {
                maxLength = chars.size();
            }
            chars.clear();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!chars.contains(c)) {
                    chars.add(c);
                } else {
                    break;
                }
            }
        }
        return chars.size() > maxLength ? chars.size() : maxLength;
    }
}