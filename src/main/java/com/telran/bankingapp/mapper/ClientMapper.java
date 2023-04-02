package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDto(Client client);
    List<ClientDTO> clientsToClientsDTO(List<Client> clients);

}
