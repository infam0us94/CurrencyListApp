package com.currencyapplication.currencylistapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class Rate(
    @ColumnInfo(name = "base")
    val base: String,
    @PrimaryKey
    val currency: String,
    @ColumnInfo(name = "rate")
    val rate: Double
)