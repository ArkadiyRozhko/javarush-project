package com.javarush.task.task38.task3810;

public @interface Revision {
    //напиши свой код
    int revision();

    Author[] authors() default {};
    Date date();
    String comment() default "";
}
