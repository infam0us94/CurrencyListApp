package com.currencyapplication.currencylistapp.data.local

import com.currencyapplication.currencylistapp.data.local.dto.CurrencyDto
import retrofit2.http.GET

interface CurrencyApi {

    @GET("exchangerates_data/latest")
    suspend fun getCurrencyList(
//        @Query("symbols") symbols: String,
//        @Query("base") base: String
    ): CurrencyDto
}