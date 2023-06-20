package com.telran.bankingapp.util;

import com.telran.bankingapp.entity.*;
import com.telran.bankingapp.entity.enums.*;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;


@UtilityClass
public class EntityCreator {
    public static final String accountID = "dce72405-ce50-49d9-9b5b-d6cf0cd61346";
    public static final String clientID = "611195b6-c02b-44cd-a8a9-92a85a521262";
    public static final String NAME = "TT 89 311045 00234355921201";

    //public static final


    public static Account getAccountEntity() {
        return new Account(
                UUID.fromString(accountID),
                getClientEntity(),
                "Checking_Account_Test",
                AccountType.CURRENT,
                AccountStatus.ACTIVE,
                10000.00,
                CurrencyType.EUR,
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0),
                null,
                null,
                null
        );
    }

    public static Account getCreateAccountEntity() {
        Agreement agreement = getAgreementEntity();
        return new Account(
                java.util.UUID.fromString(accountID),
                getClientEntity(),
                "NAME",
                AccountType.CURRENT,
                AccountStatus.PENDING,
                10000.0,
                CurrencyType.EUR,
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0),
                null,
                null,
                null
        );
    }


    public static Client getClientEntity() {
        return new Client(
                java.util.UUID.fromString(clientID),
                getManagerEntity(),
                ClientStatus.ACTIVE,
                "123123123123",
                "John",
                "Doe",
                "ivan@gmail.com",
                "Berlin",
                "+111111111111111",
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0),
                new HashSet<>()
        );
    }

    public static Agreement getAgreementEntity() {
        Agreement agreement = new Agreement();
        agreement.setId(UUID.fromString("a67cb66e-31b1-46e0-8510-47871cccbf0c"));
        agreement.setProduct(getProductEntity());
        return agreement;
    }

    public static Product getProductEntity() {
        Product product = new Product();
        product.setId(UUID.fromString("aaf2bc95-1403-4831-b8b5-8969445548ec"));
        return product;
    }


    public static Manager getManagerEntity() {
        return new Manager(
                UUID.fromString("11cf2217-b9d0-4d58-9ebb-f7f61e7206a6"),
                "John",
                "Doe",
                ManagerStatus.PENDING,
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0),
                new HashSet<>(),
                new HashSet<>()
        );
    }
}