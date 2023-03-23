package com.telran.bankingapp.controller;

import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController {
    //@Autowired
    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable(value = "id") UUID accountId) {
        //UUID accountId = UUID.fromString(id);
        return accountRepository.findById(accountId).orElseThrow(()
                -> new ResourceNotFoundException("Account", "id", accountId));
    }

//    @GetMapping("/accounts/{id}")
//    public Account getAccountByName(@PathVariable(value = "name") String name) {
//        return accountRepository.findByName(name)
//                .orElseThrow(() -> new ResourceNotFoundException("Account", "name", name));
//    }
}
