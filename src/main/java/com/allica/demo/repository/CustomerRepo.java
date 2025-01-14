package com.allica.demo.repository;

import com.allica.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    public Optional<CustomerEntity> getCustomerEntityInfo(String customerId);
}
