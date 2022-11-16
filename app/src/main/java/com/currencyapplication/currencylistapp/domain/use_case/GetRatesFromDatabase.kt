package com.currencyapplication.currencylistapp.domain.use_case

import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository

class GetRatesFromDatabase(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(base: String): List<Rate> =
        currencyRepository.getRatesFromDatabase(base)
}