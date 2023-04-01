package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.ManagerAfterCreateDTO;
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
    public ManagerAfterCreateDTO createManager(String managerId, ManagerAfterCreateDTO dto) {
        return createOrUpdateManager(UUID.fromString(managerId), dto);
    }

    @Override
    public ManagerAfterCreateDTO createManagerEmpty(ManagerAfterCreateDTO dto) {
        return createOrUpdateManager(UUID.randomUUID(), dto);
    }

    private ManagerAfterCreateDTO createOrUpdateManager(UUID managerId, ManagerAfterCreateDTO dto) {
        Manager manager = managerMapper.toManagerAfterCreate(dto);
        setManagerFields(manager, managerId);
        return saveManager(manager);
    }

    private void checkManagerDoesNotExist(String firstName, String lastName) {
        Manager existingManager = managerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (existingManager != null) {
            throw new IllegalArgumentException("A manager with the same firstName and lastName already exists");
        }
    }

    private void setManagerFields(Manager manager, UUID managerId) {
        manager.setId(managerId);
        manager.setCreatedAt(LocalDateTime.now());
        manager.setUpdatedAt(LocalDateTime.now());
    }

    private ManagerAfterCreateDTO saveManager(Manager manager) {
        checkManagerDoesNotExist(manager.getFirstName(), manager.getLastName());
        Manager savedManager = managerRepository.save(manager);
        return managerMapper.toDTOAfterCreate(savedManager);
    }

}
