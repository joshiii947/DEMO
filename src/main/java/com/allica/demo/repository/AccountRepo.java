package com.allica.demo.repository;

import com.allica.demo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Long> {

    public Optional<String> checkIfAccountNumberExist(String accountNumber);

}
