package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")

public interface AccountMapper {
    @Mapping(source = "account.client.id", target = "clientId")
    @Mapping(source = "account.client.manager.firstName", target = "managerFirstName")
    AccountDTO toDto(Account account);

    List<AccountDTO> accountsToAccountsDto(List<Account> accounts);

}
