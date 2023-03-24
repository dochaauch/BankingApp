package com.telran.bankingapp.dto;

import lombok.Value;

import java.util.List;

@Value
public class AccountListDto {
    List<AccountDTO> accountDtoList;
}
