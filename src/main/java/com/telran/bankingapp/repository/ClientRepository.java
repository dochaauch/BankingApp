package com.telran.bankingapp.repository;

import com.telran.bankingapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByTaxCode(String taxCode);
    List<Client> findClientsByAccountListIsGreaterThan(double balance);
}
