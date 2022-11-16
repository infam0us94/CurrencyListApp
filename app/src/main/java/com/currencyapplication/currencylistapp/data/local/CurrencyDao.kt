package com.currencyapplication.currencylistapp.data.local

import androidx.room.*
import com.currencyapplication.currencylistapp.domain.model.Rate

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency_table WHERE base = :base")
    suspend fun getRatesFromDatabase(base: String): List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRateToDatabase(addRate: Rate)

    @Delete
    suspend fun removeRateFromDatabase(removeRate: Rate)
}