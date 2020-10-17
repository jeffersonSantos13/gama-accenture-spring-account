package com.accademia.account.entity;

public abstract class Account {
    private String id;
    private String name;
    private double amount;
    private double income;

    public double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getType();

    public void deposit(double depositAmount) {
        this.amount += depositAmount;
    }

    public void withdraw(double withdrawAmount)  {
        if (this.amount > withdrawAmount) {
            double amountBonusWithdraw;
            amountBonusWithdraw = AccountType.valueOf(this.getType()).equals(AccountType.CHECKEINGACCOUNT) ? 0.10 : 0;

            this.income = amountBonusWithdraw;
            this.amount -= withdrawAmount;
        }
    }
}
