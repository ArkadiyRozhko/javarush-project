package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginCommand implements Command{
    private ResourceBundle validCreditCards;
    private ResourceBundle res;
    LoginCommand(){
        //ClassLoader loader= CashMachine.class.getClassLoader();
        String BaseNameCart=CashMachine.RESOURCE_PATH+"verifiedCards";
        String BaseName=CashMachine.RESOURCE_PATH+"login_en";
        validCreditCards=ResourceBundle.getBundle(BaseNameCart);
        res=ResourceBundle.getBundle(BaseName);
    }

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String cart;
        String pin;
        do{
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cart=ConsoleHelper.readString();
            pin=ConsoleHelper.readString();
            if (Pattern.matches("^\\d{12}$",cart)&&Pattern.matches("^\\d{4}$",pin)) {

                if (validCreditCards.containsKey(cart)&&validCreditCards.getString(cart).equals(pin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),cart));
                    break;
                }else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),cart));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            }else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
        }while (true);

    }
}
