package com.ismailulasunal.account.dto;

import com.ismailulasunal.account.model.Account;
import com.ismailulasunal.account.model.Customer;
import com.ismailulasunal.account.model.Transaction;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record AccountDto(
        UUID id,
        BigDecimal balance,
        LocalDateTime creationDate,
        AccountCustomerDto customer,
        Set<TransactionDto> transactions

) {
    public static AccountDto convert(Account from) {
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                AccountCustomerDto.convert(from.getCustomer()),
                from.getTransactions()
                        .stream()
                        .map(TransactionDto::convert)
                        .collect(Collectors.toSet())
                );
    }
}
