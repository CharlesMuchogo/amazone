package com.example.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "serial_number")val serial_number: String,
    @ColumnInfo(name = "quantity")val quantity: Int,
    @ColumnInfo(name = "price")val price: Int,
    @ColumnInfo(name = "image")val image: String,
    @ColumnInfo(name = "category")val category: Int
)
