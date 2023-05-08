package com.example.amazone.api

import com.example.amazone.models.Product
import retrofit2.http.GET

interface ApiService {
    @GET("product")
    suspend fun getProducts(): List<Product>
}