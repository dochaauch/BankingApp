package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.entity.Manager;
import com.telran.bankingapp.mapper.ManagerMapper;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerServiceImplTest {
    @InjectMocks
    ManagerServiceImpl service;

    @Mock
    ManagerMapper managerMapper;

    @Mock
    ManagerRepository managerRepository;

    @Test
    void testGetAllManagers() {
        // Create a list of dummy managers
        List<Manager> managers = Arrays.asList(
                EntityCreator.getManagerEntity(),
                EntityCreator.getManagerEntity(),
                EntityCreator.getManagerEntity()
        );

        // Mock the managerRepository's behavior to return the list of managers
        when(managerRepository.findAll()).thenReturn(managers);

        // Mock the managerMapper's behavior to map the managers to managerDTOs
        List<ManagerDTO> managerDTOs = Arrays.asList(
                DtoCreator.getValidManagerDto(),
                DtoCreator.getValidManagerDto(),
                DtoCreator.getValidManagerDto()
        );
        when(managerMapper.managersToManagersDTO(managers)).thenReturn(managerDTOs);

        // Call the method under test
        List<ManagerDTO> result = service.getAllManagers();

        // Verify the result
        assertEquals(managerDTOs.size(), result.size());
        assertEquals(managerDTOs, result);
    }


    @Test
    void testCreateManager_ValidData_ReturnsManagerDTO() {
        // Prepare test data
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = DtoCreator.getValidManagerDto();
        Manager managerEntity = EntityCreator.getManagerEntity();

        // Mock the behavior of managerRepository and managerMapper
        when(managerMapper.toManager(managerDTO)).thenReturn(managerEntity);
        when(managerRepository.save(managerEntity)).thenReturn(managerEntity);
        when(managerMapper.toDTO(managerEntity)).thenReturn(managerDTO);

        // Call the method under test
        ManagerDTO result = service.createManager(managerId, managerDTO);

        // Verify the result
        assertNotNull(result);
        assertEquals(managerDTO, result);

        // Verify the interactions
        verify(managerMapper).toManager(managerDTO);
        verify(managerRepository).save(managerEntity);
        verify(managerMapper).toDTO(managerEntity);
    }


    @Test
    void testCreateManager_NullData_ThrowsIllegalArgumentException() {
        // Prepare test data
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = null;

        // Call the method under test and assert exception
        assertThrows(IllegalArgumentException.class,
                () -> service.createManager(managerId, managerDTO));
    }


    @Test
    void testCreateManagerEmpty_ValidData_ReturnsManagerDTOWithGeneratedId() {
        // Prepare test data
        ManagerDTO managerDTO = DtoCreator.getValidManagerDto();
        Manager managerEntity = EntityCreator.getManagerEntity();

        // Mock the behavior of managerRepository and managerMapper
        when(managerMapper.toManager(managerDTO)).thenReturn(managerEntity);
        when(managerRepository.save(managerEntity)).thenReturn(managerEntity);
        when(managerMapper.toDTO(managerEntity)).thenReturn(managerDTO);

        // Call the method under test
        ManagerDTO result = service.createManagerEmpty(managerDTO);

        // Verify the result
        assertNotNull(result);
        assertEquals(managerDTO, result);
        assertNotNull(result.getId()); // Verify that the generated ID is not null

        // Verify the interactions
        verify(managerMapper).toManager(managerDTO);
        verify(managerRepository).save(managerEntity);
        verify(managerMapper).toDTO(managerEntity);
    }


    @Test
    void testGetManagerById_InvalidId_ThrowsIllegalArgumentException() {
        // Prepare test data
        String invalidId = "invalid-id";

        // Call the method under test and assert exception
        assertThrows(IllegalArgumentException.class,
                () -> service.getManagerById(invalidId));
    }


    @Test
    void testGetManagerById_ValidId_ReturnsManagerDTO() {
        // Prepare test data
        String validId = "11cf2217-b9d0-4d58-9ebb-f7f61e7206a6";
        UUID uuid = UUID.fromString(validId);
        Manager managerEntity = EntityCreator.getManagerEntity();
        ManagerDTO managerDTO = DtoCreator.getValidManagerDto();

        // Mock the behavior of managerRepository and managerMapper
        when(managerRepository.findById(uuid)).thenReturn(Optional.of(managerEntity));
        when(managerMapper.toDTO(managerEntity)).thenReturn(managerDTO);

        // Call the method under test
        ManagerDTO result = service.getManagerById(validId);

        // Verify the result
        assertNotNull(result);
        assertEquals(managerDTO, result);

        // Verify the interactions
        verify(managerRepository).findById(uuid);
        verify(managerMapper).toDTO(managerEntity);
    }

    @Test
    void testCreateManager_NullFirstName_ThrowsIllegalArgumentException() {
        // Prepare test data
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = DtoCreator.getNullFirstNameManagerDto();

        // Call the method under test and assert exception
        assertThrows(IllegalArgumentException.class,
                () -> service.createManager(managerId, managerDTO));
    }


    @Test
    void testGetManagerById_ManagerNotFound_ThrowsIllegalArgumentException() {
        // Prepare test data
        String validId = "11cf2217-b9d0-4d58-9ebb-f7f61e7206a6";
        UUID uuid = UUID.fromString(validId);

        // Mock the behavior of managerRepository
        when(managerRepository.findById(uuid)).thenReturn(Optional.empty());

        // Call the method under test and assert exception
        assertThrows(IllegalArgumentException.class,
                () -> service.getManagerById(validId));
    }


    @Test
    void testCreateManager_DuplicateId_ThrowsIllegalArgumentException() {
        // Prepare test data
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = DtoCreator.getValidManagerDto();

        // Mock the behavior of managerRepository
        when(managerRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(new Manager()));

        // Call the method under test and assert the exception
        assertThrows(IllegalArgumentException.class,
                () -> service.createManager(managerId, managerDTO));

        // Verify the invocation of private method checkManagerById using ReflectionTestUtils
        assertThrows(
                IllegalArgumentException.class,
                () -> ReflectionTestUtils.invokeMethod(service,
                        "checkManagerById", managerId.toString())
        );
    }


    @Test
    void testCheckManagerDoesNotExist_ManagerDoesNotExist() {
        // Prepare test data
        String firstName = "John";
        String lastName = "Doe";

        // Mock the behavior of managerRepository
        when(managerRepository.findByFirstNameAndLastName(anyString(),
                anyString())).thenReturn(null);

        // Call the private method under test using ReflectionTestUtils
        ReflectionTestUtils.invokeMethod(service, "checkManagerDoesNotExist", firstName, lastName);
    }


    @Test
    void testCheckManagerDoesNotExist_ManagerExists() {
        // Prepare test data
        String firstName = "John";
        String lastName = "Doe";

        // Create a mock existing manager
        Manager existingManager = new Manager();
        when(managerRepository.findByFirstNameAndLastName(anyString(),
                anyString())).thenReturn(existingManager);

        // Call the private method under test using ReflectionTestUtils and assert the exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ReflectionTestUtils.invokeMethod(service, "checkManagerDoesNotExist",
                        firstName, lastName));

        // Verify the exception message
        assertEquals("A manager with the same firstName and lastName already exists",
                exception.getMessage());
    }


    @Test
    void testCreateManager_NullDto_ThrowsIllegalArgumentException() {
        // Prepare test data
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = null;

        // Call the method under test and assert the exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.createManager(managerId, managerDTO));

        // Verify the exception message
        assertEquals("Invalid manager data", exception.getMessage());
    }


    @Test
    void testCreateManager_NullName_ThrowsIllegalArgumentException() {
        // Prepare test data
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = DtoCreator.getNullFirstNameManagerDto();

        // Call the method under test and assert the exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.createManager(managerId, managerDTO));

        // Verify the exception message
        assertEquals("Invalid manager data", exception.getMessage());
    }

    @Test
    void testCreateManager_NullLastName_ThrowsIllegalArgumentException() {
        // Prepare test data
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = DtoCreator.getNullLastNameManagerDto();

        // Call the method under test and assert the exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.createManager(managerId, managerDTO));

        // Verify the exception message
        assertEquals("Invalid manager data", exception.getMessage());
    }
}