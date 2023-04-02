package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.entity.Manager;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO toDTO(Manager manager);

    List<ManagerDTO> managersToManagersDTO(List<Manager> managers);

    Manager toManager(ManagerDTO managerDTO);

}
