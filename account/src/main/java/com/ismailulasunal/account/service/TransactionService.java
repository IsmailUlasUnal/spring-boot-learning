package com.ismailulasunal.account.service;

import com.ismailulasunal.account.model.Account;
import com.ismailulasunal.account.model.Transaction;
import com.ismailulasunal.account.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    protected Transaction initiateMoney(final Account account, BigDecimal amount) {
        return transactionRepository.save(
                new Transaction(amount, LocalDateTime.now(), account)
        );
    }


}
