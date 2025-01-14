package com.allica.demo.resource;

import com.allica.demo.common.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class TransactionResponseResource {

    @JsonProperty("transaction_type")
    private TransactionType transactionType;
    @JsonProperty("transaction_amount")
    private String transactionAmount;
    @JsonProperty("transaction_time")
    private LocalDateTime transactionTime;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("transaction_number")
    private String transactionNumber;

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}
