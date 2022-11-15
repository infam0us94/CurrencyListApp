package com.currencyapplication.currencylistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.currencyapplication.currencylistapp.domain.model.Rate

@Database(entities = [Rate::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao

    companion object {
        const val DATABASE_NAME = "currency_database"
    }
}