package com.telran.bankingapp.service;

import com.telran.bankingapp.dto.ManagerAfterCreateDTO;
import com.telran.bankingapp.dto.ManagerDTO;

import java.util.List;

public interface ManagerService {
    List<ManagerDTO> getAllManagers();
    ManagerDTO getManagerById(String uuid);

    ManagerAfterCreateDTO createManager(String managerId, ManagerAfterCreateDTO dto);
    ManagerAfterCreateDTO createManagerEmpty(ManagerAfterCreateDTO dto);
}
