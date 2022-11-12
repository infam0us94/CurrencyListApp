package com.currencyapplication.currencylistapp.domain.repository

import com.currencyapplication.currencylistapp.data.local.dto.CurrencyDto

interface CurrencyRepository {

    suspend fun getCurrencyList(): CurrencyDto
}