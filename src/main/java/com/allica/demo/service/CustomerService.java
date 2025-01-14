package com.allica.demo.service;

import com.allica.demo.resource.CustomerRequestResource;
import com.allica.demo.resource.CustomerResponseResource;

public interface CustomerService {

    public CustomerResponseResource saveCustomerInfo(CustomerRequestResource customerRequestResource);
}
