package com.allica.demo.controller;

import com.allica.demo.resource.TransactionRequestResource;
import com.allica.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/getTransactionInfo")
    public ResponseEntity<String> getTransactinInfo(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/saveTransactionInfo")
    public ResponseEntity<String> saveTransactionInfo(@Validated TransactionRequestResource transactionRequestResource){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
