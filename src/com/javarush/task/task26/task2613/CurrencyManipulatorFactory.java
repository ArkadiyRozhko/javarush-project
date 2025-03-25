package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String,CurrencyManipulator>map=new HashMap<>();
    private CurrencyManipulatorFactory() {
    }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        String upperCase=currencyCode.toUpperCase();
        if (map.containsKey(upperCase)) {
            return map.get(upperCase);
        }else {
            CurrencyManipulator manipulator=new CurrencyManipulator(upperCase);
            map.put(upperCase,manipulator);
            return manipulator;
        }
    }
    public static Collection<CurrencyManipulator>getAllCurrencyManipulators(){
        return map.values();
    }
}
