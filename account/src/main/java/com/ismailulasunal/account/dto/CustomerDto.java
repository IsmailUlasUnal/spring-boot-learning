package com.ismailulasunal.account.dto;

import com.ismailulasunal.account.model.Customer;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record CustomerDto(
        UUID id,
        String name,
        String surname,
        Set<CustomerAccountDto> accounts
) {
    public static CustomerDto convert(Customer from) {
        return new CustomerDto(from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts().stream().map(CustomerAccountDto::convert).collect(Collectors.toSet()));
    }

}
