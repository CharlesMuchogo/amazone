package com.example.amazone.api

import com.example.amazone.models.Product
import com.example.amazone.models.Products
import retrofit2.http.GET

interface ApiService {
    @GET("product")
    suspend fun getProducts(): Products
}