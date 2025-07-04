package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;



public class FunctionalTest {
    public void testStorage(Shortener shortener){
            String str1="String 1 and 3";
            String str2="String 2";
            String str3="String 1 and 3";

            Long id1= shortener.getId(str1);
            Long id2= shortener.getId(str2);
            Long id3= shortener.getId(str3);

        Assert.assertNotEquals(id2,id1);
        Assert.assertNotEquals(id2,id3);
        Assert.assertEquals(id1,id3);

        Assert.assertEquals(str1,shortener.getString(id1));
        Assert.assertEquals(str2,shortener.getString(id2));
        Assert.assertEquals(str3,shortener.getString(id3));

    }
    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy strategy=new HashMapStorageStrategy();
        Shortener shortener=new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy(){
        OurHashMapStorageStrategy strategy=new OurHashMapStorageStrategy();
        Shortener shortener=new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy(){
        FileStorageStrategy strategy=new FileStorageStrategy();
        Shortener shortener=new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy(){
        HashBiMapStorageStrategy strategy=new HashBiMapStorageStrategy();
        Shortener shortener=new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy(){
        DualHashBidiMapStorageStrategy strategy=new DualHashBidiMapStorageStrategy();
        Shortener shortener=new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy(){
        OurHashBiMapStorageStrategy strategy=new OurHashBiMapStorageStrategy();
        Shortener shortener=new Shortener(strategy);
        testStorage(shortener);
    }
}
