package com.telran.bankingapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telran.bankingapp.entity.Client;
import jakarta.persistence.Column;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class AccountDTO {
    String name;
    String type;
    String status;
    String balance;
    String currencyCode;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDateTime updatedAt;
}
