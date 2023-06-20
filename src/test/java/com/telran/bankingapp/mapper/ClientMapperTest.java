package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.entity.Client;
import com.telran.bankingapp.util.DtoCreator;
import com.telran.bankingapp.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ClientMapperTest {

    private final ClientMapper clientMapper = new ClientMapperImpl();

    @Test
    void testToDto() {
        // Create a Client entity for testing
        Client client = EntityCreator.getClientEntity();

        // Call the toDto() method
        ClientDTO clientDTO = clientMapper.toDto(client);

        // Assert that the DTO fields match the Client fields
        Assertions.assertEquals(client.getId().toString(), clientDTO.getId());
        Assertions.assertEquals(client.getFirstName() + " " + client.getLastName(),
                clientDTO.getFirstName() + " " + clientDTO.getLastName());
        Assertions.assertEquals(client.getManager().getId().toString(), clientDTO.getManagerId());

        // Assert that the createdAt and updatedAt fields are not null
        Assertions.assertNotNull(clientDTO.getCreatedAt());
        Assertions.assertNotNull(clientDTO.getUpdatedAt());

        // Assert that the createdAt and updatedAt fields are instances of LocalDateTime
        Assertions.assertTrue(clientDTO.getCreatedAt() instanceof LocalDateTime);
        Assertions.assertTrue(clientDTO.getUpdatedAt() instanceof LocalDateTime);
    }


    @Test
    void testClientsToClientsDTO() {
        // Create a list of Client entities for testing
        List<Client> clients = new ArrayList<>();
        clients.add(EntityCreator.getClientEntity());

        // Call the clientsToClientsDTO() method
        List<ClientDTO> clientDTOs = clientMapper.clientsToClientsDTO(clients);

        // Assert that the size of the DTO list matches the input list
        Assertions.assertEquals(clients.size(), clientDTOs.size());

        // Assert that the DTO fields match the Client fields for each element
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            ClientDTO clientDTO = clientDTOs.get(i);

            Assertions.assertEquals(client.getId().toString(), clientDTO.getId());
            Assertions.assertEquals(client.getFirstName() + " " + client.getLastName(), clientDTO.getFirstName() + " " + clientDTO.getLastName());
            Assertions.assertEquals(client.getManager().getId().toString(), clientDTO.getManagerId());
        }
    }

    @Test
    void testToClient() {
        // Create a ClientDTO instance for testing
        ClientDTO clientDTO = DtoCreator.getValidClientDto();

        // Call the toClient() method
        Client client = clientMapper.toClient(clientDTO);

        // Assert that the Client fields match the DTO fields
        Assertions.assertEquals(clientDTO.getId(), client.getId().toString());
        Assertions.assertEquals(clientDTO.getFirstName() + " " + clientDTO.getLastName(), client.getFirstName() + " " + client.getLastName());

        // Assert that the createdAt and updatedAt fields are not null
        Assertions.assertNotNull(client.getCreatedAt());
        Assertions.assertNotNull(client.getUpdatedAt());

        // Assert that the createdAt and updatedAt fields are instances of LocalDateTime
        Assertions.assertTrue(client.getCreatedAt() instanceof LocalDateTime);
        Assertions.assertTrue(client.getUpdatedAt() instanceof LocalDateTime);
    }
}