package com.ismailulasunal.account.dto;

import com.ismailulasunal.account.model.Customer;

import java.util.UUID;

public record AccountCustomerDto(
        UUID id,
        String name,
        String surname
) {
    public static AccountCustomerDto convert(Customer from) { // from can be null
        return new AccountCustomerDto(from.getId(), from.getName(), from.getSurname());
    }
}
