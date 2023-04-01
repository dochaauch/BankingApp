package com.telran.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@AllArgsConstructor
@Setter
@Getter
public class ManagerAfterCreateDTO {

    //UUID id;
    String firstName;
    String lastName;
    String status;
    LocalDateTime createdAt;

    LocalDateTime updatedAt;

}
