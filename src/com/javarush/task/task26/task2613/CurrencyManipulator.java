package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int sum = denominations.get(denomination) + count;
            denominations.put(denomination, sum);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int sum = 0;
        for (int i : denominations.keySet()
        ) {
            sum += i * denominations.get(i);
        }
        return sum;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return hasMoney() && getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> reverseDenomination = new TreeMap<>(Comparator.reverseOrder());
        reverseDenomination.putAll(denominations);
        Map<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());
        int summ = expectedAmount;
        for (Map.Entry<Integer, Integer> entry : reverseDenomination.entrySet()
        ) {
            if (summ == 0) {
                break;
            } else {
                int count = summ / entry.getKey();
                if (count != 0) {
                    summ = summ - (entry.getKey() * count);
                    result.put(entry.getKey(), count);
                    denominations.computeIfPresent(entry.getKey(), (key,value)->{
                        int newValue=entry.getValue()-count;
                        if (newValue == 0) {
                            return null;
                        }else {
                            return newValue;
                        }
                    });
                }
            }
        }
        if (summ == 0) {
            return result;
        }else {
            throw new NotEnoughMoneyException();
        }
    }
}
