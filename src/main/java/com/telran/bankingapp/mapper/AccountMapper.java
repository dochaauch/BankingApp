package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AccountMapper {
    @Mapping(source = "account.client.id", target = "clientId")
    AccountDTO toDto(Account account);

    List<AccountDTO> accountsToAccountsDto(List<Account> accounts);
}
