package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ManagerMapper {
    ManagerDTO toDTO(Manager manager);

    List<ManagerDTO> managersToManagersDTO(List<Manager> managers);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    Manager toManager(ManagerDTO managerDTO);
}
