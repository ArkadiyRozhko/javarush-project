package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command{
    private ResourceBundle res;
    DepositCommand(){
        String BaseName= CashMachine.RESOURCE_PATH+"deposit_en";
        res=ResourceBundle.getBundle(BaseName);
    }
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String curency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curency);
        String[] dig = ConsoleHelper.getValidTwoDigits(curency);
        manipulator.addAmount(Integer.parseInt(dig[0]), Integer.parseInt(dig[1]));
        ConsoleHelper.writeMessage(res.getString("success.format"));
    }
}
