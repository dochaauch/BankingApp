package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.entity.Manager;
import com.telran.bankingapp.util.DtoCreator;
import com.telran.bankingapp.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ManagerMapperTest {

    private final ManagerMapper managerMapper = new ManagerMapperImpl();

    @Test
    void testToDTO() {
        // Create a Manager entity for testing
        Manager manager = EntityCreator.getManagerEntity();

        // Call the toDTO() method
        ManagerDTO managerDTO = managerMapper.toDTO(manager);

        // Assert that the DTO fields match the Manager fields
        Assertions.assertEquals(manager.getId().toString(), managerDTO.getId());
        Assertions.assertEquals(manager.getFirstName() + " " + manager.getLastName(),
                managerDTO.getFirstName() + " " + managerDTO.getLastName());
    }

    @Test
    void testManagersToManagersDTO() {
        // Create a list of Manager entities for testing
        List<Manager> managers = new ArrayList<>();
        managers.add(EntityCreator.getManagerEntity());

        // Call the managersToManagersDTO() method
        List<ManagerDTO> managerDTOs = managerMapper.managersToManagersDTO(managers);

        // Assert that the size of the DTO list matches the input list
        Assertions.assertEquals(managers.size(), managerDTOs.size());

        // Assert that the DTO fields match the Manager fields for each element
        for (int i = 0; i < managers.size(); i++) {
            Manager manager = managers.get(i);
            ManagerDTO managerDTO = managerDTOs.get(i);

            Assertions.assertEquals(manager.getId().toString(), managerDTO.getId());
            Assertions.assertEquals(manager.getFirstName() + " " + manager.getLastName(),
                    managerDTO.getFirstName() + " " + managerDTO.getLastName());
        }
    }

    @Test
    void testToManager() {
        // Create a ManagerDTO instance for testing
        ManagerDTO managerDTO = DtoCreator.getValidManagerDto();

        // Call the toManager() method
        Manager manager = managerMapper.toManager(managerDTO);

        // Assert that the Manager fields match the DTO fields
        Assertions.assertEquals(managerDTO.getId(), manager.getId().toString());
        Assertions.assertEquals(managerDTO.getFirstName() + " " + managerDTO.getLastName(),
                manager.getFirstName() + " " + manager.getLastName());

        // Assert that the createdAt and updatedAt fields are not null
        Assertions.assertNotNull(manager.getCreatedAt());
        Assertions.assertNotNull(manager.getUpdatedAt());

        // Assert that the createdAt and updatedAt fields are instances of LocalDateTime
        Assertions.assertTrue(manager.getCreatedAt() instanceof LocalDateTime);
        Assertions.assertTrue(manager.getUpdatedAt() instanceof LocalDateTime);
    }
}