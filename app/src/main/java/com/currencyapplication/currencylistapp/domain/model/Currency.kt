package com.currencyapplication.currencylistapp.domain.model

data class Currency(
    val base: String,
    val date: String,
    val rates: List<Rate>
)