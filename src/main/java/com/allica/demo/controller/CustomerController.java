package com.allica.demo.controller;

import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.resource.CustomerResponseResource;
import com.allica.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/saveCustomerInfo")
    public ResponseEntity<CustomerResponseResource> saveCustomerInfo(@RequestBody CustomerRequestResource customerRequestResource){

        CustomerResponseResource customerResponseResource=customerService.saveCustomerInfo(customerRequestResource);

        return new ResponseEntity<>(customerResponseResource,HttpStatus.OK);
    }
}
