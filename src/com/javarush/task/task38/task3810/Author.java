package com.javarush.task.task38.task3810;

import java.lang.annotation.Repeatable;


public @interface Author {
    //напиши свой код
    String value();
    Position position() default Position.OTHER;
}
