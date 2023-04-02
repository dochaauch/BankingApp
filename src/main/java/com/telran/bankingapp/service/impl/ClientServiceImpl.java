package com.telran.bankingapp.service.impl;

import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.entity.Client;
import com.telran.bankingapp.entity.Manager;
import com.telran.bankingapp.mapper.ClientMapper;
import com.telran.bankingapp.repository.ClientRepository;
import com.telran.bankingapp.repository.ManagerRepository;
import com.telran.bankingapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

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

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.toClient(clientDTO);
        client.setId(UUID.randomUUID());
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());

        validateTaxCode(client.getTaxCode());
        validateClientId(client.getId().toString());
        Manager manager = getManager(clientDTO.getManagerId());
        client.setManager(manager);

        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    private void validateTaxCode(String taxCode) {
        Client existingClientCode = clientRepository.findByTaxCode(taxCode);
        if (existingClientCode != null) {
            throw new IllegalArgumentException("This tax code is already used in system");
        }
    }

    private void validateClientId(String clientId) {
        Client existingClientId = clientRepository.findById(UUID.fromString(clientId)).orElse(null);
        if (existingClientId != null) {
            throw new IllegalArgumentException("This id is already used");
        }
    }

    private Manager getManager(UUID managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);
        if (manager == null) {
            throw new IllegalArgumentException("Manager not found");
        }
        return manager;
    }


}
