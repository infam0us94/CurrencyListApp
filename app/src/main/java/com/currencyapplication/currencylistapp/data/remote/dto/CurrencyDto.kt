package com.currencyapplication.currencylistapp.data.remote.dto

import com.currencyapplication.currencylistapp.domain.model.Currency
import com.currencyapplication.currencylistapp.domain.model.Rate

data class CurrencyDto(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
) {
    fun toCurrencyEntity(): Currency {
        return Currency(
            base = base,
            date = date,
            rates = rates.map { Rate(base = base, currency = it.key, rate = it.value) }
        )
    }
}
