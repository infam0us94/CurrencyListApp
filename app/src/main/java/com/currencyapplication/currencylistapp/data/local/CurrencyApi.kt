package com.currencyapplication.currencylistapp.data.local

import com.currencyapplication.currencylistapp.data.local.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("exchangerates_data/latest")
    suspend fun getCurrencyList(
        @Query("base") base: String
    ): CurrencyDto
}