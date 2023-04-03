package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.entity.Manager;
import com.telran.bankingapp.mapper.ManagerMapper;
import com.telran.bankingapp.repository.ManagerRepository;
import com.telran.bankingapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    private final ManagerMapper managerMapper;
    private final ManagerRepository managerRepository;

    @Override
    public List<ManagerDTO> getAllManagers() {
        log.info("get all managers");
        List<Manager> managers = managerRepository.findAll();
        return managerMapper.managersToManagersDTO(managers);
    }

    @Override
    public ManagerDTO getManagerById(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        log.info("get manager", managerRepository.findById(uuid));
        log.info("manager {} ", uuid);
        return managerMapper.toDTO(managerRepository.findById(uuid).get());
    }


    @Override
    public ManagerDTO createManager(UUID managerId, ManagerDTO dto) {
        return createOrUpdateManager(managerId, dto);
    }

    @Override
    public ManagerDTO createManagerEmpty(ManagerDTO dto) {
        return createOrUpdateManager(UUID.randomUUID(), dto);
    }

    private ManagerDTO createOrUpdateManager(UUID managerIdL, ManagerDTO dto) {
        Manager manager = managerMapper.toManager(dto);
        setManagerFields(manager, managerIdL);
        return saveManager(manager);
    }

    private void checkManagerDoesNotExist(String firstName, String lastName) {
        Manager existingManager = managerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (existingManager != null) {
            throw new IllegalArgumentException("A manager with the same firstName and lastName already exists");
        }
    }

    private void checkManagerById(String id) {
        Manager existingManager = managerRepository.findById(UUID.fromString(id)).orElse(null);
        if (existingManager != null) {
            throw new IllegalArgumentException("This id is already used");
        }
    }

    private void setManagerFields(Manager manager, UUID managerId) {
        manager.setId(managerId);
    }


    private ManagerDTO saveManager(Manager manager) {
        checkManagerById(manager.getId().toString());
        checkManagerDoesNotExist(manager.getFirstName(), manager.getLastName());
        Manager savedManager = managerRepository.save(manager);
        return managerMapper.toDTO(savedManager);
    }

}
