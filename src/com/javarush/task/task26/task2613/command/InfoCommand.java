package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res;
    InfoCommand(){
        String BaseName=CashMachine.RESOURCE_PATH+"info_en";
        res=ResourceBundle.getBundle(BaseName);
    }
    @Override
    public void execute() {

        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulators.isEmpty()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        } else {
            for (CurrencyManipulator manipulator : manipulators
            ) {
                if (manipulator.hasMoney()) {
                System.out.println(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());}
                else {
                    ConsoleHelper.writeMessage(res.getString("no.money"));
                }
            }
        }
    }
}
