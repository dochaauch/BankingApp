package com.telran.bankingapp.controller;

import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{uuid}")
    public ClientDTO getById(@PathVariable String uuid) {
        return clientService.getClientById(uuid);
    }
}
