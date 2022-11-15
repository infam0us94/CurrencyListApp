package com.currencyapplication.currencylistapp.data.remote

import com.currencyapplication.currencylistapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class CurrencyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("apikey", BuildConfig.API_KEY)
        return chain.proceed(request.build())
    }
}