package com.example.amazone.models



data class CategoriesDTO (
    val product_categories: List<Category>,)

data class Category (
    val id: Int,
    val name: String,
    val image: String
)
