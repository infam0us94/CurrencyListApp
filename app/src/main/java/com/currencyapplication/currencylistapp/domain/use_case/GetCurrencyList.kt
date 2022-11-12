package com.currencyapplication.currencylistapp.domain.use_case

import com.currencyapplication.currencylistapp.data.local.dto.CurrencyDto
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository

class GetCurrencyList(
    private val currencyRepository: CurrencyRepository
) {
    suspend fun getCurrencyList(): CurrencyDto = currencyRepository.getCurrencyList()
}
