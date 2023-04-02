package com.telran.bankingapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class ClientDTO {
    String id;
    String firstName;
    String lastName;
    UUID managerId;
    String status;
    String taxCode;
    String email;
    String address;
    String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDateTime updatedAt;

    //String accountList;
}
