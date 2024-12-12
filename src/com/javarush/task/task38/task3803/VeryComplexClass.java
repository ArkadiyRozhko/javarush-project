package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o=5;
        String s=(String) o;
    }

    public void methodThrowsNullPointerException() {
        List list = null;
        list.size();

    }

    public static void main(String[] args) {
        VeryComplexClass aClass=new VeryComplexClass();
        //aClass.methodThrowsClassCastException();
        aClass.methodThrowsNullPointerException();

    }
}
