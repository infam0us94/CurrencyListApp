package com.currencyapplication.currencylistapp.domain.use_case

import com.currencyapplication.currencylistapp.domain.model.Currency
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository
import com.currencyapplication.currencylistapp.utils.Resource

class GetCurrencyList(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(base: String): Resource<Currency> =
        currencyRepository.getCurrencyList(base)
}
