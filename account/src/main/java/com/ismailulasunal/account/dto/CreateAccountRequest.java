package com.ismailulasunal.account.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountRequest(
        UUID customerId,
        BigDecimal initialCredit
) {

}
