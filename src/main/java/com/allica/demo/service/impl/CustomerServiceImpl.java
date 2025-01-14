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
        CustomerEntity customerEntity = getCustomerId(customerRequestResource);

        boolean isUniqueAccountNumberNotGenerated = true;
        String accountNumber = Constants.generateRandomNumber(ACCOUNT_NUMBER_LENGTH);
        while (isUniqueAccountNumberNotGenerated) {
            if (!accountRepo.checkIfAccountNumberExist(accountNumber)) {
                isUniqueAccountNumberNotGenerated = false;
            }
        }
        AccountEntity accountEntity = customerAssembler.getAccountEntityInfo(accountNumber, customerEntity);
        saveInfoIndbForAccountEntity(accountEntity);

        return customerAssembler.getCustomerResponseResource(accountEntity, customerRequestResource);
    }

    @Transactional
    private void saveInfoIndbForAccountEntity(AccountEntity accountEntity) {
        accountRepo.save(accountEntity);
    }

    private CustomerEntity getCustomerId(CustomerRequestResource customerRequestResource) {
        if (customerRequestResource.getCustomerId() == null) {
            customerRequestResource.setCustomerId(Constants.generateRandomCustomerId(customerRequestResource.getName()));
        }

        CustomerEntity customerEntityInfo  = customerRepo.getCustomerEntityInfo(customerRequestResource.getCustomerId());
        if (customerEntityInfo == null) {
            customerEntityInfo = saveInfoInDb(customerRequestResource);
        }

        return customerEntityInfo;
    }

    @Transactional
    private CustomerEntity saveInfoInDb(CustomerRequestResource customerRequestResource) {
        CustomerEntity customerEntity = customerAssembler.getCustomerEntity(customerRequestResource);
        return customerRepo.save(customerEntity);
    }
}
