package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int result = 0;
        Collection<List<V>> lists=map.values();
        for (List<V> l:lists
             ) {
            result+=l.size();
        }
        return result;
        //напишите тут ваш код
    }

    @Override
    public V put(K key, V value) {
        if (map.containsKey(key)&&(map.get(key).size())<repeatCount) {
            V result=map.get(key).get(map.get(key).size()-1);
            map.get(key).add(value);
            return result;
        } else if (map.containsKey(key)&&(map.get(key).size())==repeatCount) {
            V result=map.get(key).get(map.get(key).size()-1);
            map.get(key).remove(0);
            map.get(key).add(value);
            return result;
        }else{
            List<V>list = new ArrayList<>();
            list.add(value);
            map.put(key,list);
            return null;
        }
        //напишите тут ваш код
    }

    @Override
    public V remove(Object key) {
        V result=null;
        if (!map.containsKey(key)){
            return null;
        }else{
            if (map.get(key).size() == 0) {
                map.remove(key);
            } else if (map.get(key).size()>0) {
                result=map.get(key).get(0);
                map.get(key).remove(0);
                if (map.get(key).size() == 0) {
                    map.remove(key);
                }
            }
        }
        return result;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
        //напишите тут ваш код
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> result=new ArrayList<>();
        Collection<List<V>> lists=map.values();
        for (List<V> l:lists
        ) {
            result.addAll(l);
        }
        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
        //напишите тут ваш код
    }

    @Override
    public boolean containsValue(Object value) {
        boolean result=false;
        Collection<List<V>> lists=map.values();
        for (List<V> l:lists
             ) {
            if (!result) {
                result=l.contains(value);
            }
        }
        return result;
        //напишите тут ваш код
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}