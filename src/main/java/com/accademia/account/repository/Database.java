package com.accademia.account.repository;

import com.accademia.account.entity.Account;

import java.util.Hashtable;

public enum Database {
    INSTANCE;

    private final Hashtable<String, Account> account;

    Database() {
        this.account = new Hashtable<>();
    }

    public Hashtable account() {
        return this.account;
    }
}
