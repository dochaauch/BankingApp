package com.telran.bankingapp.entity;

import com.telran.bankingapp.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="account")
public class Account {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private UUID id;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "client_id")
        private Client client;

        @Column(name = "name")
        private String name;
        @Column(name = "type")
        private int type;
        @Column(name = "status")
        @Enumerated(EnumType.STRING)
        private AccountStatus status;
        @Column(name = "balance")
        private double balance;
        @Column(name = "currency_code")
        private int currencyCode;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
        private List<Agreement> agreementList;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "debitAccount")
        private List<Transaction> debitTransactionList;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditAccount")
        private List<Transaction> creditTransactionList;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Account)) return false;
                Account account = (Account) o;
                return Objects.equals(getId(), account.getId());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getId());
        }

}
