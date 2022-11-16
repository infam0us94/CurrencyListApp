package com.currencyapplication.currencylistapp.domain.use_case

data class CurrencyUseCases(
    val getCurrencyList: GetCurrencyList,
    val getRatesFromDatabase: GetRatesFromDatabase,
    val addRateInDatabase: AddRateInDatabase,
    val removeRateFromDatabase: RemoveRateFromDatabase
)