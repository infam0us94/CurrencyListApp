package com.currencyapplication.currencylistapp.data.repository

import com.currencyapplication.currencylistapp.data.local.CurrencyApi
import com.currencyapplication.currencylistapp.data.local.dto.CurrencyDto
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {
    override suspend fun getCurrencyList(): CurrencyDto = withContext(Dispatchers.IO) {
        return@withContext currencyApi.getCurrencyList()
    }
}