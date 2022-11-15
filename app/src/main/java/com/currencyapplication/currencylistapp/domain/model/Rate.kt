package com.currencyapplication.currencylistapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class Rate(
    @PrimaryKey
    val base: String,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "rate")
    val rate: Double,
)