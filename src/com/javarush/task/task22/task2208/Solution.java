package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println(getQuery(map));
        Map<String, String> map2 = new LinkedHashMap<>();
        map.put(null, null);
        map.put("country", null);
        map.put("city", null);
        map.put("age", null);
        System.out.println(getQuery(map2));

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        for (String key : params.keySet()) {
            if (params.get(key)!= null) {
                query.append(String.format("%s = '%s' and ",key,params.get(key)));
            }

        }
        if (query.length() > 0) {
            query.delete(query.length() - 5, query.length());
        }
        return query.toString();
    }
}
