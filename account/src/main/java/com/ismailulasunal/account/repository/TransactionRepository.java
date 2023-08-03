package com.ismailulasunal.account.repository;

import com.ismailulasunal.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    
}
