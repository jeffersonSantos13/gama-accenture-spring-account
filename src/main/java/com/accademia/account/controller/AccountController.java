package com.accademia.account.controller;

import com.accademia.account.entity.Account;
import com.accademia.account.repository.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private Hashtable accountRepository = Database.INSTANCE.account();

    @GetMapping
    public ResponseEntity<ArrayList<Account>> getListAccount() {
        ArrayList<Account> accountList = new ArrayList<Account>(
                accountRepository.values()
        );

        return ResponseEntity.ok(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        Account findAccountById = (Account) accountRepository.get(id);

        return ResponseEntity.ok(findAccountById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable String id) {
        accountRepository.remove(id);

        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
