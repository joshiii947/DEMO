package com.allica.demo.service.impl;

import com.allica.demo.assembler.CustomerAssembler;
import com.allica.demo.common.Constants;
import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.CustomerEntity;
import com.allica.demo.repository.AccountRepo;
import com.allica.demo.repository.CustomerRepo;
import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.resource.CustomerResponseResource;
import com.allica.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.allica.demo.common.Constants.ACCOUNT_NUMBER_LENGTH;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private CustomerAssembler customerAssembler;

    @Override
    public CustomerResponseResource saveCustomerInfo(CustomerRequestResource customerRequestResource) {
        getCustomerId(customerRequestResource);

        boolean isUniqueAccountNumberNotGenerated = true;
        String accountNumber = Constants.generateRandomNumber(ACCOUNT_NUMBER_LENGTH);
        while (isUniqueAccountNumberNotGenerated) {
            if (accountRepo.checkIfAccountNumberExist(accountNumber).isEmpty()) {
                isUniqueAccountNumberNotGenerated = false;
            }
        }
        AccountEntity accountEntity = customerAssembler.getAccountEntityInfo(customerRequestResource, accountNumber);
        saveInfoIndbForAccountEntity(accountEntity);

        return customerAssembler.getCustomerResponseResource(accountEntity, customerRequestResource);
    }

    @Transactional
    private void saveInfoIndbForAccountEntity(AccountEntity accountEntity) {
        accountRepo.save(accountEntity);
    }

    private void getCustomerId(CustomerRequestResource customerRequestResource) {
        if (customerRequestResource.getCustomerId() == null) {
            customerRequestResource.setCustomerId(Constants.generateRandomCustomerId(customerRequestResource.getName()));
        }

        Optional<CustomerEntity> customerEntityOptional = customerRepo.getCustomerEntityInfo(customerRequestResource.getCustomerId());
        if (customerEntityOptional.isEmpty()) {
            saveInfoInDb(customerRequestResource);
        }
    }

    @Transactional
    private void saveInfoInDb(CustomerRequestResource customerRequestResource) {
        CustomerEntity customerEntity = customerAssembler.getCustomerEntity(customerRequestResource);
        customerRepo.save(customerEntity);
    }
}
