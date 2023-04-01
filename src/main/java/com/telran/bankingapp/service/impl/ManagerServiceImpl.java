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
        Manager manager = managerMapper.toManagerAfterCreate(dto);
        manager.setId(UUID.fromString(managerId));
        manager.setCreatedAt(LocalDateTime.now());
        manager.setUpdatedAt(LocalDateTime.now());
        Manager savedManager = managerRepository.save(manager);
        return managerMapper.toDTOAfterCreate(savedManager);
    }


    @Override
    public ManagerAfterCreateDTO createManagerEmpty(ManagerAfterCreateDTO dto) {
        Manager manager = managerMapper.toManagerAfterCreate(dto);
        manager.setId(UUID.randomUUID());
        manager.setCreatedAt(LocalDateTime.now());
        manager.setUpdatedAt(LocalDateTime.now());
        Manager savedManager = managerRepository.save(manager);
        return managerMapper.toDTOAfterCreate(savedManager);
    }

}
