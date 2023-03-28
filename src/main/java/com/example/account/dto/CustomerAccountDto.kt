package com.example.account.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class CustomerAccountDto(
    val id: String,
    val balance: BigDecimal? = BigDecimal.ZERO,
    var transactions: Set<TransactionDto>?,
    val creationDate: LocalDateTime
)
