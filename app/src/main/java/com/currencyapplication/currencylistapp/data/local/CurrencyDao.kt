package com.currencyapplication.currencylistapp.data.local

import androidx.room.*
import com.currencyapplication.currencylistapp.domain.model.Rate

@Dao
interface CurrencyDao {

    @Query(
        "SELECT * FROM currency_table " +
                "WHERE base = :base " +
                " ORDER BY " +
                "CASE WHEN :currency = 1 THEN currency END ASC, " +
                "CASE WHEN :currency = 2 THEN currency END DESC, " +
                "CASE WHEN :rate = 1 THEN rate END ASC, " +
                "CASE WHEN :rate = 2 THEN rate END DESC "
    )
    fun getRatesFromDatabase(base: String, currency: Int?, rate: Int?): List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRateToDatabase(addRate: Rate)

    @Delete
    suspend fun removeRateFromDatabase(removeRate: Rate)
}