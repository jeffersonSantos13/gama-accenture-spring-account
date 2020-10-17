package com.accademia.account.entity;

public class CheckingAccount extends Account {
    @Override
    public String getType() {
        return AccountType.CHECKEINGACCOUNT.toString();
    }
}
