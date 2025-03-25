package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static ResourceBundle res;
    static {
        String BaseName= CashMachine.RESOURCE_PATH+"common_en";
        res=ResourceBundle.getBundle(BaseName);
    }

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));


    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = bis.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (s.toUpperCase().equals("EXIT")) {
            throw new InterruptOperationException();
        } else {
            return s;
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        //System.out.println("Введіть код валюти з 3-х символів");
        String currency = "";
        do {
            currency = readString();
            if (currency.length() != 3) {
                writeMessage(res.getString("invalid.data"));
                //System.out.println("Код валюти повинен складатися з 3-х символів");
            }
        } while (currency.length() != 3);
        return currency.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] result;
        String regex = "^[^0^-]\\d*\\s[^0^\\s^-]\\d*";
        Pattern pattern = Pattern.compile(regex);
        writeMessage(res.getString("choose.denomination.and.count.format"));

            //System.out.println("Введіть два цілих додатніх числа через пробіл. Перше-номінал, друге-кількість купюр");
            String digits = readString();
            while (!Pattern.matches(regex, digits)) {
                writeMessage(res.getString("invalid.data"));
                //System.out.println("Не правильно введені дані, потрібно ввести два числа серез пробіл");
                digits = readString();
            }
            result = digits.split("\\s");


        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage(res.getString("operation.INFO")+" 1");
        writeMessage(res.getString("operation.DEPOSIT")+" 2");
        writeMessage(res.getString("operation.WITHDRAW")+" 3");
        writeMessage(res.getString("operation.EXIT")+" 4");
        //System.out.println("Яку операцію потрібно виконати? 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
        try {
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askOperation();
        }
    }
    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
