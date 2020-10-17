package com.accademia.account.controller;

import com.accademia.account.entity.Account;
import com.accademia.account.entity.CheckingAccount;
import com.accademia.account.entity.SavingsAccount;
import com.accademia.account.repository.Database;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Hashtable;
import java.util.UUID;

@Controller
@RequestMapping("/saving")
public class SavingsAccountController {
    private Hashtable accountRepository = Database.INSTANCE.account();

    @PostMapping
    public ResponseEntity<Account> saveNewAccount(@RequestBody SavingsAccount account) {
        UUID uuid = UUID.randomUUID();
        account.setId(uuid.toString());

        var savedSavingsAccount = (SavingsAccount) accountRepository.put(uuid.toString(), account);

        return ResponseEntity.created(URI.create("/account/" + savedSavingsAccount))
                .body(account);
    }

    @PatchMapping("/deposit/{id}")
    public ResponseEntity<SavingsAccount> depositSavingsAccount(@PathVariable String id, @RequestBody SavingsAccount savingsAccount) {
        SavingsAccount findSavingsAccountById = (SavingsAccount) accountRepository.get(id);
        findSavingsAccountById.deposit(savingsAccount.getAmount());
        accountRepository.replace(id, findSavingsAccountById);

        return ResponseEntity.ok(findSavingsAccountById);
    }

    @PatchMapping("/withdraw/{id}")
    public ResponseEntity<SavingsAccount> withdrawSavingsAccount(@PathVariable String id, @RequestBody SavingsAccount savingsAccount) {
        SavingsAccount findSavingsAccountById = (SavingsAccount) accountRepository.get(id);
        findSavingsAccountById.withdraw(savingsAccount.getAmount());
        accountRepository.replace(id, findSavingsAccountById);

        return ResponseEntity.ok(findSavingsAccountById);
    }
}
