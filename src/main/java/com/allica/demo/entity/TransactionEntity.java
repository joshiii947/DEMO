package com.allica.demo.entity;

import com.allica.demo.common.TransactionType;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_number", nullable = false)
    private AccountEntity accountId;

    @Column(name="transaction_id",unique = true, nullable = false)
    private String transactionId;

    @Column(name="amount", nullable = false)
    private String amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionEntity;

    private LocalDateTime transactionDate;

}

