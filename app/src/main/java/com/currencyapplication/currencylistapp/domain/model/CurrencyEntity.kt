package com.currencyapplication.currencylistapp.domain.model

data class CurrencyEntity(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)