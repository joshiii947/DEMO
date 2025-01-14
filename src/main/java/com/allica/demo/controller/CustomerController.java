package com.allica.demo.controller;

import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/saveCustomerInfo")
    public ResponseEntity<String> saveCustomerInfo(@Validated CustomerRequestResource customerRequestResource){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
