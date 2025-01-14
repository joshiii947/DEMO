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
    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;
    @Column(name = "amount", nullable = false)
    private String amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionEntity;
    @Column(name = "transactionTime")
    private LocalDateTime transactionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountEntity accountId) {
        this.accountId = accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionType transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

}

