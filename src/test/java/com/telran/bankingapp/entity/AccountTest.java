package com.telran.bankingapp.entity;

import com.telran.bankingapp.entity.enums.AccountType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;


import static com.telran.bankingapp.entity.enums.AccountType.CURRENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    @InjectMocks
    private Account account;

    @Before
    public void setUp() {
        account = new Account();
    }

    @Test
    public void testGettersAndSetters() {
        //UUID id = "9c27ae99-d5b6-4fc0-8c41-5245e7a89c36";
        //Client client = "106ebf87-6fd0-474a-af49-cb9910af7f47";
        String name = "Checking";
        AccountType accountType = CURRENT;

        account.setName(name);

        assertEquals(name, account.getName());

    }

}