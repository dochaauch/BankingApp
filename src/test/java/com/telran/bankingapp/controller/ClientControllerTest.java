package com.telran.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.telran.bankingapp.util.DtoCreator;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
@DisplayName("ClientController test class")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllClientsTest() throws Exception {
        // Mock the behavior of the clientService
        List<ClientDTO> clients = new ArrayList<>();
        clients.add(DtoCreator.getValidClientDto());
        clients.add(DtoCreator.getValidClientDto());
        clients.add(DtoCreator.getValidClientDto());
        clients.add(DtoCreator.getValidClientDto());
        clients.add(DtoCreator.getValidClientDto());
        when(clientService.getAllClients()).thenReturn(clients);

        // Perform the GET request
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(clients)));
    }

    @Test
    void getByIdTest() throws Exception {
        // Mock the behavior of the clientService
        String uuid = "611195b6-c02b-44cd-a8a9-92a85a521262";
        ClientDTO client = DtoCreator.getValidClientDto();
        when(clientService.getClientById(uuid)).thenReturn(client);

        // Perform the GET request
        mockMvc.perform(get("/clients/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(client)));
    }


    @Test
    void createClientTest() throws Exception {
        ClientDTO clientDTO = DtoCreator.getValidClientDto();
        String requestBody = objectMapper.writeValueAsString(clientDTO);

        when(clientService.createClient(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/clients/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(requestBody));
    }
}