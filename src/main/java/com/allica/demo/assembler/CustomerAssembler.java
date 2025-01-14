package com.allica.demo.assembler;

import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.CustomerEntity;
import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.resource.CustomerResponseResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssembler {

    public AccountEntity getAccountEntityInfo(CustomerRequestResource resource, String accountNumber) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);

        return accountEntity;
    }

    public CustomerEntity getCustomerEntity(CustomerRequestResource customerRequestResource) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerEntity.getCustomerId());
        customerEntity.setName(customerEntity.getName());
        return customerEntity;
    }

    public CustomerResponseResource getCustomerResponseResource(AccountEntity accountEntity, CustomerRequestResource customerRequestResource) {
        CustomerResponseResource customerResponseResource = new CustomerResponseResource();
        customerResponseResource.setAccountNumber(accountEntity.getAccountNumber());
        customerResponseResource.setCustomerName(customerRequestResource.getName());
        customerResponseResource.setCustomerId(customerResponseResource.getCustomerId());
        return customerResponseResource;
    }
}
