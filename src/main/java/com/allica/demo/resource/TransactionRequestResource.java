package com.allica.demo.resource;

import com.allica.demo.common.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequestResource {

    @JsonProperty("account_number")
    @NotNull(message = "Account number cannot be null")
    private String accountNumber;
    @JsonProperty("amount")
    @NotNull(message = "Amount cannot be null") // Ensures the amount is not null
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Amount must be a valid number with up to 2 decimal places")
    private String amount;
    @JsonProperty("transaction_type")
    @NotNull(message = "TransactionType should not be null")
    private TransactionType transactionType;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}
