package com.currencyapplication.currencylistapp.domain.use_case

import com.currencyapplication.currencylistapp.domain.model.CurrencyEntity
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository
import com.currencyapplication.currencylistapp.utils.Resource

class GetCurrencyList(
    private val currencyRepository: CurrencyRepository
) {
    suspend fun getCurrencyList(base: String): Resource<CurrencyEntity> =
        currencyRepository.getCurrencyList(base)
}
