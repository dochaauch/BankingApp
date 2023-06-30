package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.entity.Client;
import com.telran.bankingapp.entity.Manager;
import com.telran.bankingapp.mapper.ClientMapper;
import com.telran.bankingapp.repository.ClientRepository;
import com.telran.bankingapp.repository.ManagerRepository;
import com.telran.bankingapp.util.DtoCreator;
import com.telran.bankingapp.util.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @InjectMocks
    ClientServiceImpl service;
    @Mock
    ClientMapper clientMapper;
    @Mock
    ClientRepository clientRepository;

    @Mock
    ManagerRepository managerRepository;

    @Test
    void testGetAllClients() {
        // Create a list of dummy clients
        List<Client> clients = Arrays.asList(EntityCreator.getClientEntity(),
                EntityCreator.getClientEntity(), EntityCreator.getClientEntity());

        // Mock the clientRepository's behavior to return the list of clients
        when(clientRepository.findAll()).thenReturn(clients);

        // Mock the clientMapper's behavior to map the clients to clientDTOs
        List<ClientDTO> clientDTOs = Arrays.asList(DtoCreator.getValidClientDto(),
                DtoCreator.getValidClientDto(), DtoCreator.getValidClientDto());
        when(clientMapper.clientsToClientsDTO(clients)).thenReturn(clientDTOs);

        // Call the method under test
        List<ClientDTO> result = service.getAllClients();

        // Verify the result
        assertEquals(clientDTOs.size(), result.size());
        assertEquals(clientDTOs, result);
    }

    @Test
    void testCreateClient() {
        // Create a dummy client DTO
        ClientDTO clientDTO = DtoCreator.getValidClientDto();

        // Create a dummy client entity
        Client clientEntity = EntityCreator.getClientEntity();

        // Create a dummy manager entity
        Manager managerEntity = EntityCreator.getManagerEntity();

        // Mock the clientMapper's behavior
        when(clientMapper.toClient(clientDTO)).thenReturn(clientEntity);
        when(clientMapper.toDto(clientEntity)).thenReturn(DtoCreator.getValidClientDto());

        // Mock the managerRepository's behavior
        UUID managerId = UUID.fromString("11cf2217-b9d0-4d58-9ebb-f7f61e7206a6");
        when(managerRepository.findById(managerId)).thenReturn(Optional.of(managerEntity));

        // Mock the clientRepository's behavior
        when(clientRepository.save(any(Client.class))).thenReturn(clientEntity);

        // Call the method under test
        ClientDTO result = service.createClient(clientDTO);

        // Verify the result
        assertNotNull(result);  // Ensure that the result is not null
        String expectedId = EntityCreator.clientID;
        assertEquals(expectedId, result.getId());
        assertEquals(clientEntity.getFirstName(), result.getFirstName());
        assertEquals(clientEntity.getLastName(), result.getLastName());

        // Verify the interactions
        verify(clientMapper).toClient(clientDTO);
        verify(managerRepository).findById(managerId);
        verify(clientRepository).save(clientEntity);
        verify(clientMapper).toDto(clientEntity);
    }


    @Test
    void testGetClientById() {
        // Create a dummy client UUID
        String uuidString = "11cf2217-b9d0-4d58-9ebb-f7f61e7206a6";
        UUID uuid = UUID.fromString(uuidString);

        // Create a dummy client entity
        Client clientEntity = EntityCreator.getClientEntity();

        // Create a dummy client DTO
        ClientDTO clientDTO = DtoCreator.getValidClientDto();

        // Mock the clientMapper's behavior
        when(clientMapper.toDto(clientEntity)).thenReturn(clientDTO);

        // Mock the clientRepository's behavior
        when(clientRepository.findById(uuid)).thenReturn(Optional.of(clientEntity));

        // Call the method under test
        ClientDTO result = service.getClientById(uuidString);

        // Verify the result
        assertNotNull(result);
        assertEquals(clientDTO, result);

        // Verify the interactions
        verify(clientRepository).findById(uuid);
        verify(clientMapper).toDto(clientEntity);
    }

    @Test
    void testValidateTaxCode_ThrowsExceptionWhenTaxCodeExists() {
        // Create a dummy tax code
        String taxCode = "123456789";

        // Create an existing client with the same tax code
        Client existingClient = new Client();
        existingClient.setTaxCode(taxCode);

        // Mock the clientRepository's behavior to return the existing client
        when(clientRepository.findByTaxCode(taxCode)).thenReturn(existingClient);

        // Call the private method under test using ReflectionTestUtils
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ReflectionTestUtils.invokeMethod(service, "validateTaxCode", taxCode));

        // Verify the exception message
        assertEquals("This tax code is already used in system", exception.getMessage());

        // Verify the interaction
        verify(clientRepository).findByTaxCode(taxCode);
    }

    @Test
    void testValidateTaxCode_NoExceptionWhenTaxCodeDoesNotExist() {
        // Create a dummy tax code
        String taxCode = "123456789";

        // Mock the clientRepository's behavior to return null, indicating that the tax code doesn't exist
        when(clientRepository.findByTaxCode(taxCode)).thenReturn(null);

        // Call the private method under test using ReflectionTestUtils
        ReflectionTestUtils.invokeMethod(service, "validateTaxCode", taxCode);

        // Verify the interaction
        verify(clientRepository).findByTaxCode(taxCode);
    }

    @Test
    void testValidateClientId_ThrowsExceptionWhenClientIdExists() {
        // Create a dummy client ID
        String clientId = "11cf2217-b9d0-4d58-9ebb-f7f61e7206a6";

        // Create an existing client with the same ID
        Client existingClient = new Client();
        existingClient.setId(UUID.fromString(clientId));

        // Mock the clientRepository's behavior to return the existing client
        when(clientRepository.findById(UUID.fromString(clientId)))
                .thenReturn(Optional.of(existingClient));

        // Call the private method under test using ReflectionTestUtils
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
            ReflectionTestUtils.invokeMethod(service, "validateClientId", clientId);
        });

        // Verify the exception message
        assertEquals("This id is already used", exception.getMessage());

        // Verify the interaction
        verify(clientRepository).findById(UUID.fromString(clientId));
    }

    @Test
    void testValidateClientId_NoExceptionWhenClientIdDoesNotExist() {
        // Create a dummy client ID
        String clientId = "11cf2217-b9d0-4d58-9ebb-f7f61e7206a6";

        // Mock the clientRepository's behavior to return null, indicating that the client ID doesn't exist
        when(clientRepository.findById(UUID.fromString(clientId)))
                .thenReturn(Optional.empty());

        // Call the private method under test using ReflectionTestUtils
        ReflectionTestUtils.invokeMethod(service, "validateClientId", clientId);

        // Verify the interaction
        verify(clientRepository).findById(UUID.fromString(clientId));
    }

    @Test
    void testGetManager_FoundManager() {
        // Create a dummy manager ID
        UUID managerId = UUID.fromString("11cf2217-b9d0-4d58-9ebb-f7f61e7206a6");

        // Create a dummy manager entity
        Manager managerEntity = EntityCreator.getManagerEntity();

        // Mock the managerRepository's behavior to return the manager entity
        when(managerRepository.findById(managerId)).thenReturn(Optional.of(managerEntity));

        // Call the private method under test using ReflectionTestUtils
        Manager result = ReflectionTestUtils.invokeMethod(service, "getManager", managerId);

        // Verify the result
        assertNotNull(result);  // Ensure that the result is not null
        assertEquals(managerEntity, result);

        // Verify the interaction
        verify(managerRepository).findById(managerId);
    }

    @Test
    void testGetManager_ManagerNotFound() {
        // Create a dummy manager ID
        UUID managerId = UUID.fromString("11cf2217-b9d0-4d58-9ebb-f7f61e7206a6");

        // Mock the managerRepository's behavior to return null, indicating that the manager doesn't exist
        when(managerRepository.findById(managerId)).thenReturn(Optional.empty());

        // Call the private method under test using ReflectionTestUtils
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
            ReflectionTestUtils.invokeMethod(service, "getManager", managerId);
        });

        // Verify the exception message
        assertEquals("Manager not found", exception.getMessage());

        // Verify the interaction
        verify(managerRepository).findById(managerId);
    }
}