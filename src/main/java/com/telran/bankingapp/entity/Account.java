package com.telran.bankingapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
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
        private int status;
        @Column(name = "balance")
        private double balance;
        @Column(name = "currency_code")
        private int currencyCode;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "agreement_account_id")
        private List<Agreement> agreements;


}
