package com.madeni.niqoz.debtapplication.adapters;

/**
 * Created by niqoz on 9/5/2017.
 */

public class getDebtors {
    private String name, amount, remaining_amount;


    public getDebtors(String name, String amount, String remaining_amount) {
        this.name = name;
        this.amount = amount;
        this.remaining_amount = remaining_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemaining_amount() {
        return remaining_amount;
    }

    public void setRemaining_amount(String remaining_amount) {
        this.remaining_amount = remaining_amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}