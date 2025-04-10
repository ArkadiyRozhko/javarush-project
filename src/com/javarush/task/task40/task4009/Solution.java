package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {

        System.out.println(getWeekdayOfBirthday("1.12.2015", "2016"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        LocalDate date=LocalDate.parse(birthday,DateTimeFormatter.ofPattern("d.M.yyyy"));
        Year year1 =Year.parse(year);
        LocalDate newDate=date.withYear(year1.getValue());
        return String.valueOf(newDate.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.ITALIAN));
    }
}
