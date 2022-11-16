package com.currencyapplication.currencylistapp.di

import android.app.Application
import androidx.room.Room
import com.currencyapplication.currencylistapp.BuildConfig
import com.currencyapplication.currencylistapp.data.local.CurrencyDatabase
import com.currencyapplication.currencylistapp.data.remote.CurrencyApi
import com.currencyapplication.currencylistapp.data.remote.CurrencyInterceptor
import com.currencyapplication.currencylistapp.data.repository.CurrencyRepositoryImpl
import com.currencyapplication.currencylistapp.domain.repository.CurrencyRepository
import com.currencyapplication.currencylistapp.domain.use_case.*
import com.currencyapplication.currencylistapp.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    fun provideCurrencyUseCases(currencyRepository: CurrencyRepository): CurrencyUseCases {
        return CurrencyUseCases(
            getCurrencyList = GetCurrencyList(currencyRepository),
            getRatesFromDatabase = GetRatesFromDatabase(currencyRepository),
            addRateInDatabase = AddRateInDatabase(currencyRepository),
            removeRateFromDatabase = RemoveRateFromDatabase(currencyRepository)
        )
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        currencyApi: CurrencyApi,
        database: CurrencyDatabase
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyApi, database.currencyDao)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): CurrencyDatabase {
        return Room.databaseBuilder(
            application,
            CurrencyDatabase::class.java,
            CurrencyDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
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

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}