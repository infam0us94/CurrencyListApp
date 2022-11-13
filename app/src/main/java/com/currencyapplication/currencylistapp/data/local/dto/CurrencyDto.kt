package com.currencyapplication.currencylistapp.data.local.dto

import com.currencyapplication.currencylistapp.domain.model.CurrencyEntity
import com.currencyapplication.currencylistapp.domain.model.RatesEntity

data class CurrencyDto(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
) {
    fun toCurrencyEntity(): CurrencyEntity {
        return CurrencyEntity(
            base = base,
            date = date,
            rates = rates.map { RatesEntity(currency = it.key, rate = it.value) }
        )
    }
}
