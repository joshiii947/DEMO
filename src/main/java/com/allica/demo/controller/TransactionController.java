package com.allica.demo.controller;

import com.allica.demo.exception.BaseRequestException;
import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;
import com.allica.demo.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactionsInfo")
    public ResponseEntity<List<TransactionResponseResource>> getTransactions(@RequestParam String accountNumber,
                                                                             @RequestParam int page, @RequestParam int size) {
        List<TransactionResponseResource> transactions = null;
        try {
            transactions = transactionService.getTransactionResponseResource(accountNumber, page, size);
        } catch (BaseRequestException baseRequestException) {
            throw new BaseRequestException(baseRequestException.getMessage());
        }
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/saveTransactionInfo")
    public ResponseEntity<TransactionResponseResource> saveTransactionInfo(@Valid @RequestBody TransactionRequestResource transactionRequestResource) {
        TransactionResponseResource transactionResponseResource = null;
        try {
            transactionResponseResource = transactionService.createTransactionForUser(transactionRequestResource);
        } catch (BaseRequestException baseRequestException) {
            throw new BaseRequestException(baseRequestException.getMessage());
        }
        return new ResponseEntity<>(transactionResponseResource, HttpStatus.OK);
    }
}
