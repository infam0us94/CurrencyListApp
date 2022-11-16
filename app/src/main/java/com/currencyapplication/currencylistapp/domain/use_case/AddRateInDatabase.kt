package com.currencyapplication.currencylistapp.domain.use_case

import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository

class AddRateInDatabase(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(rate: Rate) = currencyRepository.addRateToDatabase(rate)
}