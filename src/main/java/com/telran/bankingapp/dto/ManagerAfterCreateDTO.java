package com.telran.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@AllArgsConstructor

public class ManagerAfterCreateDTO {
    @Setter
    @Getter
    UUID id;
    String firstName;
    String lastName;
    String status;

    @Getter
    @Setter
    LocalDateTime createdAt;

    @Getter
    @Setter
    LocalDateTime updatedAt;

}
