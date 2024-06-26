package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T...elements) {
        //напишите тут ваш код

        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        return new HashSet<>(Arrays.asList(elements));
    }

    public static <K,V> HashMap<K,V> newHashMap(List <? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        HashMap<K,V> result=new HashMap<K,V>();
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException();
        }else {
            for (int i = 0; i < keys.size(); i++) {
                result.put(keys.get(i), values.get(i) );
            }
        }
        return result;
    }
}
