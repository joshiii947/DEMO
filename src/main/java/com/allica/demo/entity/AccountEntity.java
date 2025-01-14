package com.allica.demo.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private CustomerEntity customerId;

    @Column(name="account_number",unique = true, nullable = false)
    private String accountNumber;
}
