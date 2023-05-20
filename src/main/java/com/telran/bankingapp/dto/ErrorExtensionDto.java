package com.telran.bankingapp.dto;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorExtensionDto {
    String code;
    String message;
}
