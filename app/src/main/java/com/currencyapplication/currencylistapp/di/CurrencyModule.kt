package com.currencyapplication.currencylistapp.di

import com.currencyapplication.currencylistapp.BuildConfig
import com.currencyapplication.currencylistapp.data.local.CurrencyApi
import com.currencyapplication.currencylistapp.data.local.CurrencyInterceptor
import com.currencyapplication.currencylistapp.data.repository.CurrencyRepositoryImpl
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository
import com.currencyapplication.currencylistapp.domain.use_case.GetCurrencyList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(currencyRepository: CurrencyRepository): GetCurrencyList {
        return GetCurrencyList(currencyRepository)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        currencyApi: CurrencyApi
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyApi)
    }

    @Provides
    @Singleton
    fun provideRetrofitInterface(): CurrencyApi {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(CurrencyInterceptor())
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build()).build().create(CurrencyApi::class.java)
    }
}