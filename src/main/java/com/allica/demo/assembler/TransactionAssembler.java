package com.allica.demo.assembler;

import com.allica.demo.common.TransactionType;
import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.TransactionEntity;
import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionAssembler {

    public TransactionResponseResource getTransactionResponseResource(TransactionEntity transactionEntity, TransactionType transactionType) {
        TransactionResponseResource resource = new TransactionResponseResource();
        resource.setTransactionTime(transactionEntity.getTransactionDate());
        resource.setTransactionAmount(transactionEntity.getAmount());
        resource.setTransactionType(transactionType);
        resource.setAccountNumber(transactionEntity.getAccountId().getAccountNumber());
        return resource;
    }

    public TransactionEntity getTransactionEntity(AccountEntity accountEntity, TransactionRequestResource resource) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAccountId(accountEntity);
        transactionEntity.setTransactionEntity(resource.getTransactionType());
        transactionEntity.setTransactionId(UUID.randomUUID().toString());
        transactionEntity.setTransactionDate(LocalDateTime.now());
        transactionEntity.setAmount(updateNewAmount(accountEntity.getAmount(), resource.getAmount(), resource.getTransactionType()));
        return transactionEntity;
    }

    private String updateNewAmount(String accountAmount, String transactionAmount, TransactionType transactionType) {
        BigDecimal decimal = new BigDecimal(accountAmount);
        BigDecimal decimal1 = new BigDecimal(transactionAmount);
        if (transactionType.equals(TransactionType.CREDIT)) {
            return String.valueOf(decimal.add(decimal1));
        } else {
            return String.valueOf(decimal.subtract(decimal1));
        }
    }
}
