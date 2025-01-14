package com.allica.demo.repository;

import com.allica.demo.entity.AccountEntity;
import com.allica.demo.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.accountId = :accountId")
    Page<TransactionEntity> findByAccountId(@Param("accountId") AccountEntity accountId, Pageable pageable);
}
