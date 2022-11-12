package com.currencyapplication.currencylistapp.data.local.dto

data class CurrencyDto(
    val base: String,
    val date: String,
    val rates: RatesDto,
    val success: Boolean,
    val timestamp: Int
)
