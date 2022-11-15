package com.currencyapplication.currencylistapp.domain.repository

import com.currencyapplication.currencylistapp.domain.model.Currency
import com.currencyapplication.currencylistapp.utils.Resource

interface CurrencyRepository {
    suspend fun getCurrencyList(base: String): Resource<Currency>
}