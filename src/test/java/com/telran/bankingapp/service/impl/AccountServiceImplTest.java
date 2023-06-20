package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.controller.exception.AccountNotFoundException;
import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.enums.AccountStatus;
import com.telran.bankingapp.mapper.AccountMapper;
import com.telran.bankingapp.repository.AccountRepository;
import com.telran.bankingapp.util.DtoCreator;
import com.telran.bankingapp.util.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @InjectMocks
    AccountServiceImpl service;
    @Mock
    AccountMapper accountMapper;
    @Mock
    AccountRepository accountRepository;


    @Test
    void testGetAllAccounts() {
        // Create a list of dummy accounts
        List<Account> accounts = Arrays.asList(EntityCreator.getAccountEntity(),
                EntityCreator.getAccountEntity(), EntityCreator.getAccountEntity());

        // Mock the accountRepository's behavior to return the list of accounts
        when(accountRepository.findAll()).thenReturn(accounts);

        // Mock the accountMapper's behavior to map the accounts to accountDTOs
        List<AccountDTO> accountDTOs = Arrays.asList(DtoCreator.getValidAccountDto(),
                DtoCreator.getValidAccountDto(), DtoCreator.getValidAccountDto());
        when(accountMapper.accountsToAccountsDto(accounts)).thenReturn(accountDTOs);

        // Call the method under test
        List<AccountDTO> result = service.getAllAccounts();

        // Verify the result
        assertEquals(accountDTOs.size(), result.size());
        assertEquals(accountDTOs, result);
    }

    @Test
    void testGetAccountById() {
        // Create a dummy account
        Account account = EntityCreator.getAccountEntity();

        // Mock the accountRepository's behavior to return the account with the given ID
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        // Mock the accountMapper's behavior to map the account to an accountDTO
        AccountDTO accountDTO = DtoCreator.getValidAccountDto();
        when(accountMapper.toDto(account)).thenReturn(accountDTO);

        // Call the method under test
        AccountDTO result = service.getAccountById(account.getId().toString());

        // Verify the result
        assertEquals(accountDTO, result);
    }

    @Test
    void testGetAllActiveAccounts() {
        // Create a list of dummy active accounts
        List<Account> accounts = Arrays.asList(EntityCreator.getAccountEntity(),
                EntityCreator.getAccountEntity(), EntityCreator.getAccountEntity());

        // Mock the accountRepository's behavior to return the list of active accounts
        when(accountRepository.findAccountsByStatus(AccountStatus.ACTIVE)).thenReturn(accounts);

        // Mock the accountMapper's behavior to map the active accounts to accountDTOs
        List<AccountDTO> accountDTOs = Arrays.asList(DtoCreator.getValidAccountDto(),
                DtoCreator.getValidAccountDto(), DtoCreator.getValidAccountDto());
        when(accountMapper.accountsToAccountsDto(accounts)).thenReturn(accountDTOs);

        // Call the method under test
        List<AccountDTO> result = service.getAllActiveAccounts();

        // Verify the result
        assertEquals(accountDTOs.size(), result.size());
        assertEquals(accountDTOs, result);
    }

    @Test
    void testGetAccountsByProudctId() {
        // Create a dummy product ID
        String productId = "aaf2bc95-1403-4831-b8b5-8969445548ec";

        // Create a list of dummy accounts
        List<Account> accounts = Arrays.asList(EntityCreator.getAccountEntity(),
                EntityCreator.getAccountEntity(), EntityCreator.getAccountEntity());

        // Mock the accountRepository's behavior to return the list of accounts with the given product ID
        when(accountRepository.findByAgreementList_Product_Id(UUID.fromString(productId)))
                .thenReturn(accounts);

        // Mock the accountMapper's behavior to map the accounts to accountDTOs
        List<AccountDTO> accountDTOs = Arrays.asList(DtoCreator.getValidAccountDto(),
                DtoCreator.getValidAccountDto(), DtoCreator.getValidAccountDto());
        when(accountMapper.accountsToAccountsDto(accounts)).thenReturn(accountDTOs);

        // Call the method under test
        List<AccountDTO> result = service.getAccountsByProudctId(productId);

        // Verify the result
        assertEquals(accountDTOs.size(), result.size());
        assertEquals(accountDTOs, result);
    }

    @Test
    void testGetAccountById_accountNotFound() {
        // Create a dummy UUID
        UUID accountId = UUID.randomUUID();

        // Mock the accountRepository's behavior to return an empty optional
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Call the method under test and verify the exception
        assertThrows(AccountNotFoundException.class,
                () -> service.getAccountById(accountId.toString()));
    }
}