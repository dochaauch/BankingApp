package com.telran.bankingapp.service;

import com.telran.bankingapp.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(String id);

    ClientDTO createClient(ClientDTO clientDTO);
}
