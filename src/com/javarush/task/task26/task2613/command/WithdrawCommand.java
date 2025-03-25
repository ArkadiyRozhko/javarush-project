package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{
    private ResourceBundle res;
    WithdrawCommand(){
        String BaseName= CashMachine.RESOURCE_PATH+"withdraw_en";
        res=ResourceBundle.getBundle(BaseName);
    }
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true){
            try {
                String curency = ConsoleHelper.askCurrencyCode();
                CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curency);
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                try {
                    int summ=Integer.parseInt(ConsoleHelper.readString());
                    if (summ <= 0) {
                        ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                        continue;
                    }
                    if (manipulator.isAmountAvailable(summ)) {
                        Map<Integer,Integer> result=manipulator.withdrawAmount(summ);
                        for (Integer k:result.keySet()
                             ) {
                            ConsoleHelper.writeMessage("\t"+ k+" - "+ result.get(k));

                        }
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),summ,curency));

                        break;
                    }else {
                        ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                        continue;
                    }

                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }

        }

    }
}
