package com.telran.bankingapp.repository;

import com.telran.bankingapp.entity.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    //@Query("from Card c where c.status =:status and c.account.credit.creditOrder.clientId =:clientId")
    //List<Account> getAccountById(UUID accountId);
}
