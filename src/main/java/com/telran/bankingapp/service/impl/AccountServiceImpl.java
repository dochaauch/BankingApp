package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.enums.AccountStatus;
import com.telran.bankingapp.exception.AccountNotFoundException;
import com.telran.bankingapp.exception.ErrorMessage;
import com.telran.bankingapp.mapper.AccountMapper;
import com.telran.bankingapp.repository.AccountRepository;
import com.telran.bankingapp.service.AccountService;
import jakarta.transaction.Transactional;
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

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.accountsToAccountsDto(accounts);
    }


    @Override
    @Transactional
    public AccountDTO getAccountById(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        log.info("Get an account with id {}", uuid);
        return accountMapper.toDto(accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)));
    }

    @Override
    public List<AccountDTO> getAllActiveAccounts() {
        List<Account> accounts = accountRepository.findAccountsByStatus(AccountStatus.ACTIVE);
        return accountMapper.accountsToAccountsDto(accounts);
    }


    @Override
    public List<AccountDTO> getAccountsByProudctId(String productId) {
        List<Account> accounts = accountRepository.findByAgreementList_Product_Id(
                UUID.fromString(productId));
        return accountMapper.accountsToAccountsDto(accounts);
    }
}
