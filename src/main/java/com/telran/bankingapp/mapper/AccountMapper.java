package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.dto.AccountListDto;
import com.telran.bankingapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")

public interface AccountMapper {
    AccountDTO toDto(Account account);
    List<AccountDTO> accountsToAccountsDto(List<Account> accounts);

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("stringToUuid")
    default UUID stringToUuid(String string) {
        return UUID.fromString(string);
    }

}
