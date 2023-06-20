package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.enums.AccountStatus;
import com.telran.bankingapp.entity.enums.AccountType;
import com.telran.bankingapp.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.telran.bankingapp.util.DtoCreator;

class AccountMapperTest {
    private AccountMapper accountMapper;

    private List<Account> accountList;

    @BeforeEach
    void setUp() {
        accountMapper = new AccountMapperImpl();
        // Initialize the accountList with some test data
        accountList = Arrays.asList(EntityCreator.getAccountEntity(), EntityCreator.getCreateAccountEntity());
    }

    @Test
    void testToDto() {
        // Create an Account instance for testing
        Account account = EntityCreator.getAccountEntity();

        // Convert the Account instance to AccountDTO using the mapper
        AccountDTO accountDTO = accountMapper.toDto(account);

        // Verify the mapping results
        Assertions.assertEquals(account.getClient().getId().toString(), accountDTO.getClientId());
        Assertions.assertEquals(account.getClient().getManager().getFirstName(),
                accountDTO.getManagerFirstName());
        Assertions.assertEquals(account.getName(), accountDTO.getName());
        Assertions.assertEquals(account.getType().name(), accountDTO.getType());
        Assertions.assertEquals(account.getStatus().name(), accountDTO.getStatus());
        Assertions.assertEquals(String.valueOf(account.getBalance()), accountDTO.getBalance());
        Assertions.assertEquals(account.getCurrencyCode().name(), accountDTO.getCurrencyCode());
        Assertions.assertEquals(account.getCreatedAt(), accountDTO.getCreatedAt());
        Assertions.assertEquals(account.getUpdatedAt(), accountDTO.getUpdatedAt());
    }


    @Test
    void testAccountsToAccountsDto() {
        // Convert the list of Account instances to a list of AccountDTO instances
        List<AccountDTO> accountDTOList = accountMapper.accountsToAccountsDto(accountList);

        // Verify the mapping results
        Assertions.assertEquals(accountList.size(), accountDTOList.size());

        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            AccountDTO accountDTO = accountDTOList.get(i);

            Assertions.assertEquals(account.getClient().getId().toString(), accountDTO.getClientId());
            Assertions.assertEquals(account.getClient().getManager().getFirstName(), accountDTO.getManagerFirstName());
            Assertions.assertEquals(account.getName(), accountDTO.getName());
            Assertions.assertEquals(account.getType().name(), accountDTO.getType());
            Assertions.assertEquals(account.getStatus().name(), accountDTO.getStatus());
            Assertions.assertEquals(String.valueOf(account.getBalance()), accountDTO.getBalance());
            Assertions.assertEquals(account.getCurrencyCode().name(), accountDTO.getCurrencyCode());
            Assertions.assertEquals(account.getCreatedAt(), accountDTO.getCreatedAt());
            Assertions.assertEquals(account.getUpdatedAt(), accountDTO.getUpdatedAt());
        }
    }

    @Test
    void testToDto_NullAccount_ReturnsNull() {
        AccountDTO accountDTO = accountMapper.toDto(null);
        Assertions.assertNull(accountDTO);
    }

    @Test
    void testAccountsToAccountsDto_NullList_ReturnsNull() {
        List<AccountDTO> accountDTOList = accountMapper.accountsToAccountsDto(null);
        Assertions.assertNull(accountDTOList);
    }

    @Test
    void testAccountsToAccountsDto_EmptyList_ReturnsEmptyList() {
        List<AccountDTO> accountDTOList = accountMapper.accountsToAccountsDto(List.of());
        Assertions.assertNotNull(accountDTOList);
        Assertions.assertTrue(accountDTOList.isEmpty());
    }


    @Test
    void testToDto_WithCustomValues() {
        // Create an Account instance with custom values for testing
        Account account = EntityCreator.getAccountEntity();
        // Convert the Account instance to AccountDTO using the mapper
        AccountDTO accountDTO = accountMapper.toDto(account);
        // Verify the mapping results
        Assertions.assertEquals(account.getClient().getId().toString(), accountDTO.getClientId());
        Assertions.assertEquals(account.getClient().getManager().getFirstName(), accountDTO.getManagerFirstName());
        Assertions.assertEquals(account.getName(), accountDTO.getName());
        Assertions.assertEquals(account.getType().name(), accountDTO.getType());
        Assertions.assertEquals(account.getStatus().name(), accountDTO.getStatus());
        Assertions.assertEquals(String.valueOf(account.getBalance()), accountDTO.getBalance());
        Assertions.assertEquals(account.getCurrencyCode().name(), accountDTO.getCurrencyCode());
        Assertions.assertEquals(account.getCreatedAt(), accountDTO.getCreatedAt());
        Assertions.assertEquals(account.getUpdatedAt(), accountDTO.getUpdatedAt());
    }
}