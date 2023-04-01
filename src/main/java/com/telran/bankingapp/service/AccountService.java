package com.telran.bankingapp.service;

import com.telran.bankingapp.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(String uuid);

}
