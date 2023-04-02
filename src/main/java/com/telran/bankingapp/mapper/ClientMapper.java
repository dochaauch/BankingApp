package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "client.manager.id", target = "managerId")
    ClientDTO toDto(Client client);
    List<ClientDTO> clientsToClientsDTO(List<Client> clients);
    Client toClient(ClientDTO clientDTO);

}
