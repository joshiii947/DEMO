package com.allica.demo.assembler;

import com.allica.demo.common.TransactionType;
import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.TransactionEntity;
import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionAssemblerTest {

    @InjectMocks
    private TransactionAssembler transactionAssembler;

    @Mock
    private AccountEntity accountEntity;

    @Mock
    private TransactionRequestResource transactionRequestResource;

    private TransactionEntity transactionEntity;
    private TransactionResponseResource transactionResponseResource;

    @BeforeEach
    public void setup() {
        transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(UUID.randomUUID().toString());
        transactionEntity.setTransactionDate(LocalDateTime.now());
        transactionEntity.setAmount("500.00");

        AccountEntity account = new AccountEntity();
        account.setAccountNumber("12345");
        account.setAmount("1000.00");
        transactionEntity.setAccountId(account);

        transactionResponseResource = new TransactionResponseResource();
        transactionResponseResource.setTransactionNumber(transactionEntity.getTransactionId());
        transactionResponseResource.setTransactionTime(transactionEntity.getTransactionDate());
        transactionResponseResource.setTransactionAmount("500.00");
        transactionResponseResource.setAccountNumber("12345");
    }

    @Test
    public void testGetTransactionResponseResourceWithPage() {
        Page<TransactionEntity> transactionEntityPage = new PageImpl<>(List.of(transactionEntity));

        List<TransactionResponseResource> response = transactionAssembler.getTransactionResponseResource(transactionEntityPage);

        assertEquals(1, response.size());
        assertEquals(transactionEntity.getAccountId().getAccountNumber(), response.get(0).getAccountNumber());
    }

    @Test
    public void testGetTransactionResponseResourceWithEntity() {
        TransactionResponseResource response = transactionAssembler.getTransactionResponseResource(transactionEntity, TransactionType.CREDIT);

        assertEquals(transactionEntity.getTransactionId(), response.getTransactionNumber());
        assertEquals(transactionEntity.getAccountId().getAccountNumber(), response.getAccountNumber());
    }

    @Test
    public void testGetTransactionEntity() {
        when(accountEntity.getAmount()).thenReturn("1000.00");
        when(transactionRequestResource.getTransactionType()).thenReturn(TransactionType.CREDIT);
        when(transactionRequestResource.getAmount()).thenReturn("200.00");

        TransactionEntity result = transactionAssembler.getTransactionEntity(accountEntity, transactionRequestResource);

        assertEquals(accountEntity, result.getAccountId());
        assertEquals("1200.00", result.getAmount()); // Updated amount for CREDIT
    }
}
