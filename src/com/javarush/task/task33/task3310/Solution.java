package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        HashMapStorageStrategy hashMapStorageStrategy=new HashMapStorageStrategy();
        testStrategy(hashMapStorageStrategy,10000);

    }

    static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long>ids=new HashSet<>();
        for (String str:strings
             ) {
            ids.add(shortener.getId(str));
        }
        return ids;
    }
    static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strs=new HashSet<>();
        for (Long key:keys
             ) {
            strs.add(shortener.getString(key));
        }
        return strs;
    }
    static void testStrategy(StorageStrategy strategy, long elementsNumber){
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> strings=new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener=new Shortener(strategy);

        Date before=new Date();
        Set<Long>ids=getIds(shortener,strings);
        Date after=new Date();
        System.out.println("Time getIDS = "+(after.getTime()-before.getTime())+" msec");

        Date before1=new Date();
        Set<String> strings1=getStrings(shortener,ids);
        Date after1=new Date();
        System.out.println("Time getStrings = "+(after1.getTime()-before1.getTime())+" msec");

        if (strings.containsAll(strings1)) {
            System.out.println("Тест пройден.");
        }else {
            System.out.println("Тест не пройден.");
        }



    }
}
