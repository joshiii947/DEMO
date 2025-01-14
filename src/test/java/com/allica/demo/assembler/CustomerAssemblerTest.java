package com.allica.demo.assembler;


import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.CustomerEntity;
import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.resource.CustomerResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class CustomerAssemblerTest {

    @InjectMocks
    private CustomerAssembler customerAssembler;

    private CustomerRequestResource customerRequestResource;
    private CustomerEntity customerEntity;
    private AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerAssembler = new CustomerAssembler();

        // Setting up test data
        customerRequestResource = new CustomerRequestResource();
        customerRequestResource.setCustomerId("CUST123");
        customerRequestResource.setName("John Doe");

        customerEntity = new CustomerEntity();
        customerEntity.setCustomerId("CUST123");
        customerEntity.setName("John Doe");

        accountEntity = new AccountEntity();
        accountEntity.setAccountNumber("ACC12345");
        accountEntity.setCustomerId(customerEntity);
        accountEntity.setAmount("0");
    }

    @Test
    void testGetAccountEntityInfo() {
        // Call the method
        AccountEntity result = customerAssembler.getAccountEntityInfo("ACC12345", customerEntity);

        // Assertions
        assertNotNull(result);
        assertEquals("ACC12345", result.getAccountNumber());
        assertEquals(customerEntity, result.getCustomerId());
        assertEquals("0", result.getAmount()); // Default amount should be 0
    }

    @Test
    void testGetCustomerEntity() {
        // Call the method
        CustomerEntity result = customerAssembler.getCustomerEntity(customerRequestResource);

        // Assertions
        assertNotNull(result);
        assertEquals("CUST123", result.getCustomerId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetCustomerResponseResource() {
        // Call the method
        CustomerResponseResource result = customerAssembler.getCustomerResponseResource(accountEntity, customerRequestResource);

        // Assertions
        assertNotNull(result);
        assertEquals("ACC12345", result.getAccountNumber());
        assertEquals("John Doe", result.getCustomerName());
        assertEquals("CUST123", result.getCustomerId());
    }
}
