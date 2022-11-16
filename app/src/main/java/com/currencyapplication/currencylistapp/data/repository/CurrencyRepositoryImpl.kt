package com.currencyapplication.currencylistapp.data.repository

import com.currencyapplication.currencylistapp.data.local.CurrencyDao
import com.currencyapplication.currencylistapp.data.remote.CurrencyApi
import com.currencyapplication.currencylistapp.data.remote.SafeApiCall
import com.currencyapplication.currencylistapp.domain.model.Currency
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository
import com.currencyapplication.currencylistapp.utils.Resource

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi,
    private val currencyDao: CurrencyDao
) : CurrencyRepository, SafeApiCall {

    override suspend fun getCurrencyList(base: String): Resource<Currency> =
        safeApiCall { currencyApi.getCurrencyList(base).toCurrencyEntity() }

    override suspend fun getRatesFromDatabase(base: String): List<Rate> =
        currencyDao.getRatesFromDatabase(base)

    override suspend fun addRateToDatabase(rate: Rate) = currencyDao.addRateToDatabase(rate)

    override suspend fun removeRateFromDatabase(rate: Rate) =
        currencyDao.removeRateFromDatabase(rate)
}