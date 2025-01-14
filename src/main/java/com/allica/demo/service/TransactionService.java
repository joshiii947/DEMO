package com.allica.demo.service;

import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;

public interface TransactionService {

    public TransactionResponseResource createTransactionForUser(TransactionRequestResource resource);


}
