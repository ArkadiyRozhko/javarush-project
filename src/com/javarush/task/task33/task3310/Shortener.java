package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    private Long lastId= 0L;
    private StorageStrategy storageStrategy;

    private Long id;
    private String string;

    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        } else{
            lastId+=1;
            storageStrategy.put(lastId,string);
            return lastId;
        }
    }

    public synchronized String getString(Long id){
        return storageStrategy.getValue(id);
    }

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

}
