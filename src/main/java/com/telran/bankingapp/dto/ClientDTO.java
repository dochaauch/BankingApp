package com.telran.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class ClientDTO {
    UUID id;
    String firstName;
    String lastName;
}
