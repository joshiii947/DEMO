package com.allica.demo.controller;

import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.resource.TransactionResponseResource;
import com.allica.demo.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getTransactionInfo/{transactionId}/{accountId}")
    public ResponseEntity<String> getTransactinInfo(@PathVariable("transactionId") String transactionId,
                                                    @PathVariable("accountId") String accountId){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/saveTransactionInfo")
    public ResponseEntity<TransactionResponseResource> saveTransactionInfo(@Valid @RequestBody TransactionRequestResource transactionRequestResource){
        TransactionResponseResource transactionResponseResource=transactionService.createTransactionForUser(transactionRequestResource);
        return new ResponseEntity<>(transactionResponseResource,HttpStatus.OK);
    }
}
