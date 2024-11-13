package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static com.javarush.task.task39.task3913.Event.WRITE_MESSAGE;

public class Solution {
    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date1;
        Date date2;
        try {
            date1 = format.parse("13.09.2013 05:12:00");
            date2 = format.parse("21.10.2021 12:00:55");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));

        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println(logParser.getNumberOfUniqueIPs(null, date2));
        System.out.println(logParser.getNumberOfUniqueIPs(date1, null));
        System.out.println(logParser.getNumberOfUniqueIPs(date1, date2));
        System.out.println("________________________________________________________________________________________");

        System.out.println(logParser.getUniqueIPs(null, null));
        System.out.println(logParser.getUniqueIPs(null, date2));
        System.out.println(logParser.getUniqueIPs(date1, null));
        System.out.println(logParser.getUniqueIPs(date1,date2));

        System.out.println("________________________________________________________________________________________");
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, date2));
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", date1, null));
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", date1, date2));

        System.out.println("________________________________________________________________________________________");
        System.out.println(logParser.getIPsForEvent(WRITE_MESSAGE, null, null));
        System.out.println(logParser.getIPsForEvent(WRITE_MESSAGE, null, date2));
        System.out.println(logParser.getIPsForEvent(WRITE_MESSAGE, date1, null));
        System.out.println(logParser.getIPsForEvent(WRITE_MESSAGE, date1, date2));

    }
}