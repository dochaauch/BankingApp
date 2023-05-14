//package com.telran.bankingapp.controller;
//
//import com.telran.bankingapp.dto.AccountDTO;
//import com.telran.bankingapp.service.AccountService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import util.DtoCreator;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//
//@AutoConfigureMockMvc
//
//@WebMvcTest(AccountController.class)
//@DisplayName("AccountController test class")
//class AccountControllerTest2 {
//
//    @MockBean
//    AccountService accountService;
//
//    @Autowired
//    AccountController accountController;
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//
//    @Test
//    void getAllAccountsTest() throws Exception {
//        List<AccountDTO> accountDTOList = new ArrayList<>();
//        AccountDTO accountDto = DtoCreator.getAccountDto();
//        accountDTOList.add(accountDto);
//
//        Mockito.when(accountService.getAllAccounts()).thenReturn(accountDTOList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
//        .andExpect(jsonPath("$.name").value(accountDTOList.get(0).getName()))
//        .andExpect(jsonPath("$.type").value(accountDTOList.get(0).getType()));
//
//
//    }
//
//    @Test
//    void getAccountById() {
//    }
//
//    @Test
//    void getAllActiveAccounts() {
//    }
//
//    @Test
//    void getAccountsByProductId() {
//    }
//}