package com.currencyapplication.currencylistapp.data.repository

import com.currencyapplication.currencylistapp.data.local.CurrencyApi
import com.currencyapplication.currencylistapp.data.local.SafeApiCall
import com.currencyapplication.currencylistapp.domain.model.CurrencyEntity
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository
import com.currencyapplication.currencylistapp.utils.Resource

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi
) : CurrencyRepository, SafeApiCall {

    override suspend fun getCurrencyList(): Resource<CurrencyEntity> {
        return safeApiCall { currencyApi.getCurrencyList().toCurrencyEntity() }
    }
}