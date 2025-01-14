package com.allica.demo.assembler;

import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.CustomerEntity;
import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.resource.CustomerResponseResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssembler {

    public AccountEntity getAccountEntityInfo(String accountNumber, CustomerEntity customerEntity) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);
        accountEntity.setCustomerId(customerEntity);
        accountEntity.setAmount("0");
        return accountEntity;
    }
    public CustomerEntity getCustomerEntity(CustomerRequestResource customerRequestResource) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerRequestResource.getCustomerId());
        customerEntity.setName(customerRequestResource.getName());
        return customerEntity;
    }

    public CustomerResponseResource getCustomerResponseResource(AccountEntity accountEntity, CustomerRequestResource customerRequestResource) {
        CustomerResponseResource customerResponseResource = new CustomerResponseResource();
        customerResponseResource.setAccountNumber(accountEntity.getAccountNumber());
        customerResponseResource.setCustomerName(customerRequestResource.getName());
        customerResponseResource.setCustomerId(customerRequestResource.getCustomerId());
        return customerResponseResource;
    }
}
