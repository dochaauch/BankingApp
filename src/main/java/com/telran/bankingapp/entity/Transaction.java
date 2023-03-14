package com.telran.bankingapp.entity;

import com.telran.bankingapp.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "debit_account_id")
    private Account debitAccount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "credit_account_id")
    private Account creditAccount;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "amount")
    private double amount;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction transaction = (Transaction) o;
        return Objects.equals(getId(), transaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
