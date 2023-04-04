package com.telran.bankingapp.controller;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.repository.AccountRepository;
import com.telran.bankingapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("")
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{uuid}")
    public AccountDTO getAccountById(@PathVariable String uuid) {
        return accountService.getAccountById(uuid);
    }

    @GetMapping("/active")
    public List<AccountDTO> getAllActiveAccounts() {
        return accountService.getAllActiveAccounts();
    }

    @GetMapping("/by_product/{productId}")
    public List<AccountDTO> getAccountsByProductId(@PathVariable String productId){
        return accountService.getAccountsByProudctId(productId);
    }
}
