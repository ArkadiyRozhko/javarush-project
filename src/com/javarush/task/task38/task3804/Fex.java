package com.javarush.task.task38.task3804;

public class Fex {
    public static Throwable getExcept(Enum e){
        if (e == null) {
            return new IllegalArgumentException();
        }
        String s=e.toString().toLowerCase().replace('_',' ');
        String mess=s.substring(0,1).toUpperCase()+s.substring(1);
        if (e instanceof ApplicationExceptionMessage ) {
            return new Exception(mess);
        } else if (e instanceof DatabaseExceptionMessage) {
            return new RuntimeException(mess);
        }else if (e instanceof UserExceptionMessage){
            return new Error(mess);
        }else {
            return new IllegalArgumentException();
        }
    }
}
