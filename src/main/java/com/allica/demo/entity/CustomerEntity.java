package com.allica.demo.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name="customerName", nullable = false)
    private String name;
}
