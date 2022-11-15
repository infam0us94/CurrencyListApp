package com.currencyapplication.currencylistapp.data.local

import androidx.room.*
import com.currencyapplication.currencylistapp.domain.model.Rate

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency_table")
    suspend fun getCurrencyFromDatabase(): List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrencyToDatabase(addCurrency: Rate)

    @Delete
    suspend fun removeCurrencyFromDatabase(removeCurrency: Rate)
}