package com.allica.demo.resource;

import com.allica.demo.common.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestResource {

    private String accountNumber;

    private String transactionId;

    private String amount;

    private TransactionType transactionType;

    private String transactionDate;
}
