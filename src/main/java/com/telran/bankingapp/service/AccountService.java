package com.telran.bankingapp.service;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.dto.AccountListDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(String uuid);

}
