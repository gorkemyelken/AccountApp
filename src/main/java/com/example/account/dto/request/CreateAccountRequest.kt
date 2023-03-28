package com.example.account.dto.request

import java.math.BigDecimal

data class CreateAccountRequest (
    val customerId: String,
    val initialCredit: BigDecimal
    )