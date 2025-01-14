package com.allica.demo.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResource {

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private String customerId;

    private String accountNumber;

}
