package com.telran.bankingapp.service;

import com.telran.bankingapp.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(String uuid);

    List<AccountDTO> getAllActiveAccounts();

    List<AccountDTO> getAccountsByProudctId(String productId);
}
