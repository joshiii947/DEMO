package com.allica.demo.validation;

import com.allica.demo.common.TransactionType;
import com.allica.demo.entity.AccountEntity;
import com.allica.demo.exception.BaseRequestException;
import com.allica.demo.resource.TransactionRequestResource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountValidation {


    public static void isValidaAccountAndTransactionValid(TransactionRequestResource resource, AccountEntity accountEntity){
        if(accountEntity==null){
            throw new BaseRequestException("Account is not exist and not created {}",resource.getAccountNumber());
        }

        if(resource.getTransactionType().equals(TransactionType.DEBIT) && isAmountLessThan(accountEntity.getAmount(),resource.getAmount())){
            throw new BaseRequestException("Amount debited is less than the amount in the accountBalance {}",resource.getAccountNumber());
        }
    }

    public static boolean isAmountLessThan(String amount1, String amount2) {
        // Convert strings to BigDecimal
        BigDecimal value1 = new BigDecimal(amount1);
        BigDecimal value2 = new BigDecimal(amount2);

        // Compare values
        return value1.compareTo(value2) < 0;
    }

}
