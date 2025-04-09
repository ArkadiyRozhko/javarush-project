package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("20.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        Calendar calendar=Calendar.getInstance();
        if (date.matches("^\\d{1,2}[.]\\d{1,2}[.]\\d{4}\\s{1}\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$")) {
            String format=String.format("dd.MM.yyyy HH:mm:ss");
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            try {
                Date date1=dateFormat.parse(date);
                calendar.setTime(date1);
                System.out.println("День: "+calendar.get(Calendar.DATE));
                System.out.println("День недели: "+(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY?7:calendar.get(Calendar.DAY_OF_WEEK)-1));
                System.out.println("День месяца: "+calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println("День года: "+calendar.get(Calendar.DAY_OF_YEAR));
                System.out.println("Неделя месяца: "+calendar.get(Calendar.WEEK_OF_MONTH));
                System.out.println("Неделя года: "+calendar.get(Calendar.WEEK_OF_YEAR));
                System.out.println("Месяц: "+(calendar.get(Calendar.MONTH)+1));
                System.out.println("Год: "+calendar.get(Calendar.YEAR));
                System.out.println("AM или PM: "+calendar.getDisplayName(Calendar.AM_PM,Calendar.SHORT,Locale.ENGLISH ));
                System.out.println("Часы: "+calendar.get(Calendar.HOUR));
                System.out.println("Часы дня: "+calendar.get(Calendar.HOUR_OF_DAY));
                System.out.println("Минуты: "+calendar.get(Calendar.MINUTE));
                System.out.println("Секунды: "+calendar.get(Calendar.SECOND));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        } else if (date.matches("^\\d{1,2}[.]\\d{1,2}[.]\\d{4}$")) {
            String format=String.format("dd.MM.yyyy");
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            try {
                Date date1=dateFormat.parse(date);
                calendar.setTime(date1);
                System.out.println("День: "+calendar.get(Calendar.DATE));
                System.out.println("День недели: "+(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY?7:calendar.get(Calendar.DAY_OF_WEEK)-1));
                System.out.println("День месяца: "+calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println("День года: "+calendar.get(Calendar.DAY_OF_YEAR));
                System.out.println("Неделя месяца: "+calendar.get(Calendar.WEEK_OF_MONTH));
                System.out.println("Неделя года: "+calendar.get(Calendar.WEEK_OF_YEAR));
                System.out.println("Месяц: "+(calendar.get(Calendar.MONTH)+1));
                System.out.println("Год: "+calendar.get(Calendar.YEAR));

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            
        } else if (date.matches("^\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$")) {
            String format=String.format("HH:mm:ss");
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            try {
                Date date1=dateFormat.parse(date);
                calendar.setTime(date1);
                System.out.println("AM или PM: "+calendar.getDisplayName(Calendar.AM_PM,Calendar.SHORT,Locale.ENGLISH ));
                System.out.println("Часы: "+calendar.get(Calendar.HOUR));
                System.out.println("Часы дня: "+calendar.get(Calendar.HOUR_OF_DAY));
                System.out.println("Минуты: "+calendar.get(Calendar.MINUTE));
                System.out.println("Секунды: "+calendar.get(Calendar.SECOND));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
