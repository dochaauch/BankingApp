package com.telran.bankingapp.repository;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.enums.AccountStatus;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findAccountsByStatus(AccountStatus status);


    @Query("SELECT a FROM Account a " +
            "JOIN a.client c " +
            "JOIN c.manager m " +
            "JOIN m.productList p " +
            "WHERE p.id = :productId")
    List<Account> findAccountsByProductId(@Param("productId") UUID productId);

    List<Account> findByClientManagerProductListId(UUID productId);

}
