package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) {
        if (string == null) {throw new TooShortStringException();}
        String[]arr = string.split(" ");
        StringBuilder sb = new StringBuilder();

        if (arr.length <5){
            throw new TooShortStringException();
        }else {
            for(int i=1;i<=4;i++){
                sb.append(arr[i]+" ");
            }
        }
        return sb.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
