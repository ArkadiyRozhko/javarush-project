package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;
    public static Operation getAllowableOperationByOrdinal(Integer i){
        if (i>=1&&i<=4) {
            return Operation.values()[i];
        }else {
            throw new IllegalArgumentException("Числа повинні бути від 1 до 4");
        }
    }
}
