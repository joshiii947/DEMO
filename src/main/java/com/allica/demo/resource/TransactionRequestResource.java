package com.allica.demo.resource;

import com.allica.demo.common.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequestResource {

    private String accountNumber;

    private String transactionId;

    private String amount;

    private TransactionType transactionType;

    private String transactionDate;
}
