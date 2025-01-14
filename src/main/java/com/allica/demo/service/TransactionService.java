package com.allica.demo.service;

import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;

import java.util.List;

public interface TransactionService {
    public TransactionResponseResource createTransactionForUser(TransactionRequestResource resource);

    public List<TransactionResponseResource> getTransactionResponseResource(String accountNumber,Integer page,Integer size);

}
