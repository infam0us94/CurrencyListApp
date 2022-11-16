package com.currencyapplication.currencylistapp.domain.repository

import com.currencyapplication.currencylistapp.domain.model.Currency
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.utils.Resource

interface CurrencyRepository {

    suspend fun getCurrencyList(base: String): Resource<Currency>

    suspend fun getRatesFromDatabase(base: String): List<Rate>

    suspend fun addRateToDatabase(rate: Rate)

    suspend fun removeRateFromDatabase(rate: Rate)
}