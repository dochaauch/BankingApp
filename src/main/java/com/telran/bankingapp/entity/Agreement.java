package com.telran.bankingapp.entity;

import com.telran.bankingapp.entity.enums.AccountProductStatus;
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
@Table(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "interest_rate")
    private double interestRate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountProductStatus status;
    @Column(name = "sum")
    private double sum;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return id.equals(agreement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
