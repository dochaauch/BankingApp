package com.telran.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllAccounts() throws Exception {
        List<AccountDTO> accountDTOList = Arrays.asList(
                new AccountDTO("Account 1", "Type 1", "Status 1", "100.00", "USD", "Client 1", LocalDateTime.now(), LocalDateTime.now(), "Manager 1", "Product 1"),
                new AccountDTO("Account 2", "Type 2", "Status 2", "200.00", "EUR", "Client 2", LocalDateTime.now(), LocalDateTime.now(), "Manager 2", "Product 2")
        );

        given(accountService.getAllAccounts()).willReturn(accountDTOList);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(accountDTOList)));
    }

    @Test
    public void testGetAccountById() throws Exception {
        UUID accountId = UUID.randomUUID();
        AccountDTO accountDTO = new AccountDTO("Account 1", "Type 1", "Status 1", "100.00", "USD", "Client 1", LocalDateTime.now(), LocalDateTime.now(), "Manager 1", "Product 1");

        given(accountService.getAccountById(accountId.toString())).willReturn(accountDTO);

        mockMvc.perform(get("/accounts/{uuid}", accountId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(accountDTO)));
    }

    @Test
    public void testGetAllActiveAccounts() throws Exception {
        List<AccountDTO> accountDTOList = Arrays.asList(
                new AccountDTO("Account 1", "Type 1", "ACTIVE", "100.00", "USD", "Client 1", LocalDateTime.now(), LocalDateTime.now(), "Manager 1", "Product 1"),
                new AccountDTO("Account 2", "Type 2", "ACTIVE", "200.00", "EUR", "Client 2", LocalDateTime.now(), LocalDateTime.now(), "Manager 2", "Product 2")
        );

        given(accountService.getAllActiveAccounts()).willReturn(accountDTOList);

        mockMvc.perform(get("/accounts/active"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(accountDTOList)));
    }

    @Test
    public void testGetAccountsByProductId() throws Exception {
        UUID productId = UUID.randomUUID();
        List<AccountDTO> accountDTOList = Arrays.asList(
                new AccountDTO("Account 1", "Type 1", "Status 1", "100.00", "USD", "Client 1", LocalDateTime.now(), LocalDateTime.now(), "Manager 1", productId.toString()),
                new AccountDTO("Account 2", "Type 2", "Status 2", "200.00", "EUR", "Client 2", LocalDateTime.now(), LocalDateTime.now(), "Manager 2", productId.toString())
        );

        given(accountService.getAccountsByProudctId(productId.toString())).willReturn(accountDTOList);

        mockMvc.perform(get("/accounts/by_product/{productId}", productId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(accountDTOList)));
    }

}

