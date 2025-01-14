package com.allica.demo.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerResponseResource {
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("customer_id")
    private String customerId;
}
