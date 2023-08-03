package com.ismailulasunal.account.dto;

import com.ismailulasunal.account.model.Account;
import com.ismailulasunal.account.model.Transaction;
import com.ismailulasunal.account.model.TransactionType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        TransactionType transactionType,
        BigDecimal amount,
        LocalDateTime transactionDate
) {

    public static TransactionDto convert(Transaction from) {
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
