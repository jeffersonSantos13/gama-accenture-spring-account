package com.accademia.account.controller;

import com.accademia.account.entity.Account;
import com.accademia.account.entity.AccountType;
import com.accademia.account.entity.CheckingAccount;
import com.accademia.account.entity.SavingsAccount;
import com.accademia.account.repository.Database;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

@Controller
@RequestMapping("/checking")
public class CheckingAccountController {

    private Hashtable accountRepository = Database.INSTANCE.account();

    @PostMapping
    public ResponseEntity<Account> saveNewAccount(@RequestBody CheckingAccount account) {
        UUID uuid = UUID.randomUUID();
        account.setId(uuid.toString());

        var savedCheckingAccount = (CheckingAccount) accountRepository.put(uuid.toString(), account);

        return ResponseEntity.created(URI.create("/account/" + savedCheckingAccount))
                .body(account);
    }

    @PatchMapping("/deposit/{id}")
    public ResponseEntity<CheckingAccount> depositCheckingAccount(@PathVariable String id, @RequestBody CheckingAccount checkingAccount) {
        CheckingAccount checkingAccountById = (CheckingAccount) accountRepository.get(id);
        checkingAccountById.deposit(checkingAccount.getAmount());
        accountRepository.replace(id, checkingAccountById);

        return ResponseEntity.ok(checkingAccountById);
    }

    @PatchMapping("/withdraw/{id}")
    public ResponseEntity<CheckingAccount> withdrawCheckingAccount(@PathVariable String id, @RequestBody CheckingAccount checkingAccount) {
        CheckingAccount findCheckingAccountById = (CheckingAccount) accountRepository.get(id);
        findCheckingAccountById.withdraw(checkingAccount.getAmount());
        accountRepository.replace(id, findCheckingAccountById);

        return ResponseEntity.ok(findCheckingAccountById);
    }

}
