package com.telran.bankingapp.entity;

import com.telran.bankingapp.entity.enums.AccountStatus;
import com.telran.bankingapp.entity.enums.AccountType;
import com.telran.bankingapp.entity.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
    private Set<Agreement> agreementList;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "debitAccount")
    private Set<Transaction> debitTransactionList;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "creditAccount")
    private Set<Transaction> creditTransactionList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
