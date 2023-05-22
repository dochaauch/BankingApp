package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.Agreement;
import com.telran.bankingapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Iterator;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface AccountMapper {
    @Mapping(source = "account.client.id", target = "clientId")
    @Mapping(source = "account.client.manager.firstName", target = "managerFirstName")
    @Mapping(target = "productId", expression = "java(getProductId(account))")
    //@Mapping(source = "account.agreement.product.id", target = "productId")
    //@Mapping(target = "productId", source = "account.agreementList.product.id")
    AccountDTO toDto(Account account);

    default String getProductId(Account account) {
        if (account.getAgreementList() != null && !account.getAgreementList().isEmpty()) {
            Agreement agreement = account.getAgreementList().stream().findFirst().orElse(null);
            return agreement.getProduct().getId().toString();
        }
        return null;
    }

    List<AccountDTO> accountsToAccountsDto(List<Account> accounts);
}
