package com.currencyapplication.currencylistapp.data.remote

import com.currencyapplication.currencylistapp.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("exchangerates_data/latest")
    suspend fun getCurrencyList(
        @Query("base") base: String
    ): CurrencyDto
}