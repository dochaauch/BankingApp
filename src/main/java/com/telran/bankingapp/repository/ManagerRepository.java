package com.telran.bankingapp.repository;

import com.telran.bankingapp.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    Manager findByFirstNameAndLastName(String firstName, String lastName);

}


