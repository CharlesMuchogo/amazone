package com.example.amazone.models


data class Product (
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val quantity: String,
    val category: Category
    )

