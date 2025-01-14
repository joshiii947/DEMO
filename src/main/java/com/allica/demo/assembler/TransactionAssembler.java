package com.allica.demo.assembler;

import com.allica.demo.common.TransactionType;
import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.TransactionEntity;
import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionAssembler {

    // Retrieve TransactionResponseReource
    public List<TransactionResponseResource> getTransactionResponseResource(Page<TransactionEntity> transactionEntityPage) {
        return transactionEntityPage.getContent().stream()
                .map(this::convertToResponseResource)
                .toList();
    }

    // Helper method to convert TransactionEntity to TransactionResponseResource
    private TransactionResponseResource convertToResponseResource(TransactionEntity entity) {
        TransactionResponseResource response = new TransactionResponseResource();
        response.setAccountNumber(entity.getAccountId().getAccountNumber());
        response.setTransactionType(entity.getTransactionEntity());
        response.setTransactionTime(entity.getTransactionDate());
        response.setTransactionAmount(entity.getAmount());
        return response;
    }

    // Used to return the transactionResponseResource
    public TransactionResponseResource getTransactionResponseResource(TransactionEntity transactionEntity, TransactionType transactionType) {
        TransactionResponseResource resource = new TransactionResponseResource();
        resource.setTransactionTime(transactionEntity.getTransactionDate());
        resource.setTransactionAmount(transactionEntity.getAmount());
        resource.setTransactionType(transactionType);
        resource.setAccountNumber(transactionEntity.getAccountId().getAccountNumber());
        resource.setTransactionNumber(transactionEntity.getTransactionId());
        return resource;
    }

    // To save the info in the db for transactionEntity
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
