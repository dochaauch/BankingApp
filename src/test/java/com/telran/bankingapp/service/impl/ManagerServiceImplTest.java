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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
}