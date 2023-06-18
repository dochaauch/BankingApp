package com.telran.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.service.AccountService;
import com.telran.bankingapp.service.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;
import util.DtoCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ManagerController.class)
@DisplayName("Manager controller test")
class ManagerControllerTest {
    @MockBean
    private ManagerService managerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGetAllManagers() throws Exception {
        // Mock the behavior of the clientService
        List<ManagerDTO> managers = new ArrayList<>();
        managers.add(DtoCreator.getValidManagerDto());
        managers.add(DtoCreator.getValidManagerDto());
        when(managerService.getAllManagers()).thenReturn(managers);

        // Perform the GET request
        mockMvc.perform(get("/managers"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content()
                        .json(objectMapper.writeValueAsString(managers)));
    }



    @Test
    void testGetManagerById() throws Exception {
        // Mock the behavior of the managerService
        String managerId = "123";
        ManagerDTO mockManager = DtoCreator.getValidManagerDto();
        when(managerService.getManagerById(managerId)).thenReturn(mockManager);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/managers/{uuid}", managerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testCreateManager() throws Exception {
        // Mock the behavior of the managerService
        String managerId = "11cf2217-b9d0-4d58-9ebb-f7f61e7206a6";
        ManagerDTO managerDto = DtoCreator.getValidManagerDto();
        ManagerDTO createdManager = DtoCreator.getValidManagerDto();
        when(managerService.createManager(any(UUID.class), eq(managerDto)))
                .thenReturn(createdManager);

        // Perform the POST request
        mockMvc.perform(post("/managers/new/{managerId}", managerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(managerDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testCreateManagerEmpty() throws Exception {
        // Mock the behavior of the managerService
        ManagerDTO managerDTO = DtoCreator.getValidManagerDto();
        String requestBody = objectMapper.writeValueAsString(managerDTO);
        when(managerService.createManagerEmpty(any(ManagerDTO.class))).thenReturn(managerDTO);

        // Perform the POST request
        mockMvc.perform(post("/managers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").exists());
    }
}