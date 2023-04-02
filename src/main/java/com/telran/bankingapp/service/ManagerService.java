package com.telran.bankingapp.service;

import com.telran.bankingapp.dto.ManagerDTO;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    List<ManagerDTO> getAllManagers();

    ManagerDTO getManagerById(String uuid);

    ManagerDTO createManager(UUID managerId, ManagerDTO dto);

    ManagerDTO createManagerEmpty(ManagerDTO dto);
}
