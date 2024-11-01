package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String>strings, Set<Long> ids){
        Date before=new Date();
        for (String str:strings
             ) {
            ids.add(shortener.getId(str));
        }
        Date after=new Date();
        return after.getTime()- before.getTime();
    }
    public long getTimeToGetStrings(Shortener shortener,Set<Long> ids, Set<String> strings){
        Date before=new Date();
        for (Long id:ids
        ) {
            strings.add(shortener.getString(id));
        }
        Date after=new Date();
        return after.getTime()- before.getTime();
    }
    @Test
    public void testHashMapStorage(){
        HashMapStorageStrategy hashMapStorageStrategy=new HashMapStorageStrategy();
        Shortener shortener1=new Shortener(hashMapStorageStrategy);
        HashBiMapStorageStrategy biMapStorageStrategy=new HashBiMapStorageStrategy();
        Shortener shortener2=new Shortener(biMapStorageStrategy);
        Set<String>origStrings=new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long>ids1=new HashSet<>();
        Set<Long>ids2=new HashSet<>();
        Long Time1Shot=getTimeToGetIds(shortener1,origStrings,ids1);
        Long Time2Shot=getTimeToGetIds(shortener2,origStrings,ids2);
        Assert.assertTrue( Time1Shot>Time2Shot);

        long TimeStrShot1=getTimeToGetStrings(shortener1,ids1,origStrings);
        long TimeStrShot2=getTimeToGetStrings(shortener2,ids2,origStrings);
        Assert.assertEquals(TimeStrShot1,TimeStrShot2,30);

    }
}
