package com.allica.demo.service.impl;

import com.allica.demo.assembler.CustomerAssembler;
import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.CustomerEntity;
import com.allica.demo.repository.AccountRepo;
import com.allica.demo.repository.CustomerRepo;
import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.resource.CustomerResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private AccountRepo accountRepo;

    @Mock
    private CustomerAssembler customerAssembler;

    private CustomerRequestResource customerRequestResource;
    private CustomerEntity customerEntity;
    private AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerRequestResource = new CustomerRequestResource();
        customerRequestResource.setName("John Doe");
        customerRequestResource.setCustomerId("CUST123");

        customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("John Doe");

        accountEntity = new AccountEntity();
        accountEntity.setAccountNumber("ACC12345");
        accountEntity.setCustomerId(customerEntity);
    }

    @Test
    void testSaveCustomerInfo_NewCustomer() {
        // Mocking
        when(customerRepo.getCustomerEntityInfo(anyString())).thenReturn(null);
        when(customerAssembler.getCustomerEntity(any(CustomerRequestResource.class))).thenReturn(customerEntity);
        when(customerRepo.save(any(CustomerEntity.class))).thenReturn(customerEntity);
        when(accountRepo.checkIfAccountNumberExist(anyString())).thenReturn(false);
        when(customerAssembler.getAccountEntityInfo(anyString(), any(CustomerEntity.class))).thenReturn(accountEntity);
        when(customerAssembler.getCustomerResponseResource(any(AccountEntity.class), any(CustomerRequestResource.class)))
                .thenReturn(new CustomerResponseResource());

        // Call the service method
        CustomerResponseResource response = customerService.saveCustomerInfo(customerRequestResource);

        // Verify interactions and assert
        verify(customerRepo, times(1)).getCustomerEntityInfo(anyString());
        verify(accountRepo, times(1)).save(any(AccountEntity.class));
        assertNotNull(response);
    }

    @Test
    void testSaveCustomerInfo_ExistingCustomer() {
        // Mocking
        when(customerRepo.getCustomerEntityInfo(anyString())).thenReturn(customerEntity);
        when(accountRepo.checkIfAccountNumberExist(anyString())).thenReturn(false);
        when(customerAssembler.getAccountEntityInfo(anyString(), any(CustomerEntity.class))).thenReturn(accountEntity);
        when(customerAssembler.getCustomerResponseResource(any(AccountEntity.class), any(CustomerRequestResource.class)))
                .thenReturn(new CustomerResponseResource());

        // Call the service method
        CustomerResponseResource response = customerService.saveCustomerInfo(customerRequestResource);

        // Verify interactions and assert
        verify(customerRepo, times(1)).getCustomerEntityInfo(anyString());
        verify(accountRepo, times(1)).save(any(AccountEntity.class));
        assertNotNull(response);
    }
}
