package com.accademia.account.entity;

public class SavingsAccount extends Account {

    @Override public String getType() {
        return AccountType.SAVINGSACCOUNT.toString();
    }

}
