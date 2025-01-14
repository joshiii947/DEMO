package com.allica.demo.repository;

import com.allica.demo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AccountEntity a WHERE a.accountNumber = :accountNumber")
    public boolean checkIfAccountNumberExist(@Param("accountNumber") String accountNumber);

}
