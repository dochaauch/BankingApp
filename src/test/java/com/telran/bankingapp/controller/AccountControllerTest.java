package com.telran.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.exception.DataNotFoundException;
import com.telran.bankingapp.exception.ErrorMessage;
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

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.DtoCreator.CREATEDAT;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@DisplayName("AccountController test class")

class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllAccountsGetStatus() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/accounts").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk());
    }


    @Test
    void getAllAccountsTest() throws Exception {
        // create a list of AccountDTO objects to be returned by the mocked service
        List<AccountDTO> accountDTOList = Arrays.asList(DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto());

        // configure the mock service to return the list of AccountDTO objects
        when(accountService.getAllAccounts()).thenReturn(accountDTOList);

        // perform a GET request to the "/accounts" endpoint
        MvcResult mvcResult = mockMvc.perform(get("/accounts")).andExpect(status().isOk()).andReturn();

        // extract the response body as a JSON string
        String responseBody = mvcResult.getResponse().getContentAsString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // deserialize the JSON string into a list of AccountDTO objects
        List<AccountDTO> responseList = objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, AccountDTO.class));


        // assert that the response list matches the mocked list
        assertTrue(responseList.size() > 0);
        AccountDTO firstDto = responseList.get(0);
        assertEquals(firstDto.getCreatedAt().format(formatter), CREATEDAT);
    }

    @Test
    void getAccountByIdTest() throws Exception {
        // Arrange
        String validUuidFromLiquibase = "dce72405-ce50-49d9-9b5b-d6cf0cd61346";
        AccountDTO accountDTO = DtoCreator.getValidAccountDto();
        when(accountService.getAccountById(validUuidFromLiquibase)).thenReturn(accountDTO);

        // Act
        MvcResult mvcResult = mockMvc.perform(get("/accounts/" + validUuidFromLiquibase)).andExpect(status().isOk()).andReturn();

        // Assert
        String json = mvcResult.getResponse().getContentAsString();
        AccountDTO responseDto = objectMapper.readValue(json, AccountDTO.class);
        //String expectedJson = objectMapper.writeValueAsString(responseDto);
        String expectedJson = "{\"name\":\"Checking_Account1\"," + "\"type\":\"CURRENT\"," + "\"status\":\"ACTIVE\"," + "\"balance\":\"1000.00\"," + "\"currencyCode\":\"EUR\"," + "\"clientId\":\"611195b6-c02b-44cd-a8a9-92a85a521262\"," + "\"createdAt\":\"2023-03-14T00:00:00\"," + "\"updatedAt\":\"2023-03-14T00:00:00\"," + "\"managerFirstName\":\"John\"}";
        assertEquals(expectedJson, json);
    }

    @Test
    void getAccountByWrongIdTest() throws Exception {
        String wrongId = "wrong";
        when(accountService.getAccountById(wrongId)).thenThrow(new DataNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
        mockMvc.perform(get("/accounts/{uuid}", wrongId)).andExpect(status().is4xxClientError());
    }


    @Test
    void getAllActiveAccountsTest() throws Exception {
        List<AccountDTO> accountDTOList = Arrays.asList(DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto());
        when(accountService.getAllActiveAccounts()).thenReturn(accountDTOList);
        mockMvc.perform(get("/accounts/active")).andExpect(status().isOk());
    }


    @Test
    void getAccountsByProductIdTest() throws Exception {
        List<AccountDTO> accountDTOList = Arrays.asList(DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto(), DtoCreator.getTestAccountDto());
        when(accountService.getAccountsByProudctId("1")).thenReturn(accountDTOList);
        mockMvc.perform(get("/accounts/by_product/1")).andExpect(status().isOk());
    }


    @Test
    void getAccountsByWrongProductIdTest() throws Exception {
        when(accountService.getAccountsByProudctId("wrong")).thenThrow(new DataNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
        mockMvc.perform(get("/accounts/product/{productId}", "wrong")).andExpect(status().is4xxClientError());
    }
}