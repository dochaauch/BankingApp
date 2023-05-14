package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Iterator;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})

public interface AccountMapper {
    @Mapping(source = "account.client.id", target = "clientId")
    @Mapping(source = "account.client.manager.firstName", target = "managerFirstName")
    //@Mapping(source = "account.client.manager.productList[0].id", target =  "productId")
    /*@Mapping(expression = "java(account.getClient().getManager().getProductList().isEmpty() " +
            "? null : account.getClient().getManager().getProductList().get(0).getId().toString())",
            target = "productId")*/
    //@Mapping(expression = "java(getProductId(account))", target = "productId")
    AccountDTO toDto(Account account);

    List<AccountDTO> accountsToAccountsDto(List<Account> accounts);

    default String getProductId(Account account) {
        Iterator<Product> iterator = account.getClient().getManager().getProductList().iterator();
        if (iterator.hasNext()) {
            return iterator.next().getId().toString();
        } else {
            return null;
        }
    }
}
