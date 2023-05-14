package com.telran.bankingapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import util.DtoCreator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@DisplayName("AccountController test class")
//@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles("test")
//@ContextConfiguration(classes = {AccountController.class, AccountService.class, AccountRepository.class, AccountMapper.class, Account.class, AccountDto.class, AccountMapperTest.class}

class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllAccountsGetStatus() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders
                .get("/accounts")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }


//    @Test
//    void getAllAccountsTest() throws Exception {
//        // create a list of AccountDTO objects to be returned by the mocked service
//        List<AccountDTO> accountDTOList = Arrays.asList(DtoCreator.getAccountDto(),
//                DtoCreator.getAccountDto(),
//                DtoCreator.getAccountDto(),
//                DtoCreator.getAccountDto()
//                );
//
//        // configure the mock service to return the list of AccountDTO objects
//        when(accountService.getAllAccounts()).thenReturn(accountDTOList);
//
//        // perform a GET request to the "/accounts" endpoint
//        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // extract the response body as a JSON string
//        String responseBody = mvcResult.getResponse().getContentAsString();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//
//        // deserialize the JSON string into a list of AccountDTO objects
//        List<AccountDTO> responseList = objectMapper.readValue(responseBody,
//                new TypeReference<List<AccountDTO>>() {});
//
//        // assert that the response list matches the mocked list
//        assertTrue(responseList.size() > 0);
//        AccountDTO firstDto = responseList.get(0);
//        assertEquals(firstDto.getCreatedAt(),
//                LocalDateTime.parse("14.03.2023", formatter));
//    }

    @Test
    void getAccountById() {
    }

    @Test
    void getAllActiveAccounts() {
    }

    @Test
    void getAccountsByProductId() {
    }
}