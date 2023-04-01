package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.mapper.AccountMapper;
import com.telran.bankingapp.repository.AccountRepository;
import com.telran.bankingapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public List<AccountDTO> getAllAccounts(){
        log.info("Get all accounts");
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.accountsToAccountsDto(accounts);
    }


    @Override
    public AccountDTO getAccountById(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        log.info("get account", accountRepository.findById(uuid));
        log.info("account {} ", uuid);
        return accountMapper.toDto(accountRepository.findById(uuid).get());
    }
}
