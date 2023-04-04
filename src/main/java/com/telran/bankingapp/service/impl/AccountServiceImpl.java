package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.enums.AccountStatus;
import com.telran.bankingapp.mapper.AccountMapper;
import com.telran.bankingapp.repository.AccountRepository;
import com.telran.bankingapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<AccountDTO> getAllActiveAccounts() {
        List<Account> accounts = accountRepository.findAccountsByStatus(AccountStatus.ACTIVE);
        /*List<Account> accounts = accountRepository.findAll()
                .stream()
                .filter(account -> account.getStatus()== AccountStatus.ACTIVE)
                .collect(Collectors.toList());*/
        return accountMapper.accountsToAccountsDto(accounts);
    }

    @Override
    public List<AccountDTO> getAccountsByProudctId(String productId) {
        //List<Account> accounts = accountRepository.findAccountsByProductId(UUID.fromString(productId));
        List<Account> accounts = accountRepository.findByClientManagerProductListId(UUID.fromString(productId));
        return accountMapper.accountsToAccountsDto(accounts);
    }


}
