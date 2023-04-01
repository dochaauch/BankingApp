package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AccountMapper {
    @Mapping(source = "account.client.id", target = "clientId")
    AccountDTO toDto(Account account);


    /*default String map(Client client) {
        return client.toString();
    }*/
    List<AccountDTO> accountsToAccountsDto(List<Account> accounts);


//    @Named("uuidToString")
//    default String uuidToString(UUID uuid) {
//        return uuid.toString();
//    }
//
//    @Named("stringToUuid")
//    default UUID stringToUuid(String string) {
//        return UUID.fromString(string);
//    }

}
