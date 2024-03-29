package com.telran.bankingapp.repository;

import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findAccountsByStatus(AccountStatus status);

    List<Account> findByAgreementList_Product_Id(UUID productId);
}
