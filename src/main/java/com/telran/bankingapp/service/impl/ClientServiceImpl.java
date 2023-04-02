package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.entity.Client;
import com.telran.bankingapp.mapper.ClientMapper;
import com.telran.bankingapp.repository.ClientRepository;
import com.telran.bankingapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.clientsToClientsDTO(clients);
    }

    @Override
    public ClientDTO getClientById(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return clientMapper.toDto(clientRepository.findById(uuid).get());
    }
}
