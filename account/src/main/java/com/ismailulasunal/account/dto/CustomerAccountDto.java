package com.ismailulasunal.account.dto;

import com.ismailulasunal.account.model.Account;
import com.ismailulasunal.account.model.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record CustomerAccountDto(
        UUID id,
        BigDecimal balance,
        LocalDateTime creationDate,
        Set<TransactionDto> transactions

) {
    public static CustomerAccountDto convert(Account from) {
        return new CustomerAccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                from.getTransactions().stream().map(TransactionDto::convert).collect(Collectors.toSet()));
    }
}
