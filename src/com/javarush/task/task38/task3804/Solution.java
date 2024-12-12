package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

import static com.javarush.task.task38.task3804.ApplicationExceptionMessage.UNHANDLED_EXCEPTION;

public class Solution {

    public static Class getFactoryClass() {
        return Fex.class;
    }

    public static void main(String[] args) {

        Throwable throwable= Fex.getExcept(UNHANDLED_EXCEPTION);

    }
}