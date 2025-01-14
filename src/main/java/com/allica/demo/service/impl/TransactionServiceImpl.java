package com.allica.demo.service.impl;

import com.allica.demo.assembler.TransactionAssembler;
import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.TransactionEntity;
import com.allica.demo.repository.AccountRepo;
import com.allica.demo.repository.TransactionRepo;
import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;
import com.allica.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.allica.demo.validation.AccountValidation.isValidaAccountAndTransactionValid;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TransactionAssembler transactionAssembler;

    @Override
    public TransactionResponseResource createTransactionForUser(TransactionRequestResource resource) {
        AccountEntity accountEntity = accountRepo.getAccountInfo(resource.getAccountNumber());
        isValidaAccountAndTransactionValid(resource, accountEntity);
        TransactionEntity transactionEntity = transactionAssembler.getTransactionEntity(accountEntity, resource);
        transactionEntity = transactionRepo.save(transactionEntity);
        accountEntity.setAmount(transactionEntity.getAmount());
        accountRepo.save(accountEntity);
        return transactionAssembler.getTransactionResponseResource(transactionEntity,resource.getTransactionType());
    }

    @Override
    public List<TransactionResponseResource> getTransactionResponseResource(String accountNumber, Integer page,Integer limit) {
        Pageable pageable=PageRequest.of(page, limit);
        AccountEntity accountEntity=accountRepo.getAccountInfo(accountNumber);
        Page<TransactionEntity> transactionEntities=transactionRepo.findByAccountId(accountEntity,pageable);
        List<TransactionResponseResource> transactionResponseResources=transactionAssembler.getTransactionResponseResource(transactionEntities);
        return transactionResponseResources;
    }
}
