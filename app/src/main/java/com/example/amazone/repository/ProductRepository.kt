package com.example.amazone.repository

import com.example.amazone.api.ApiService
import com.example.amazone.api.ProductApiState
import com.example.amazone.models.CategoriesDTO
import com.example.amazone.models.Product
import com.example.amazone.models.Products
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Module
@InstallIn(SingletonComponent::class)
class ProductRepository(val apiService: ApiService, ) {
    suspend fun getProducts():Flow<ProductApiState<Products>>{
        return  flow {
            var product = apiService.getProducts()
            emit(ProductApiState.success(product))
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getCategories():Flow<ProductApiState<CategoriesDTO>>{
        return  flow {
            var categories = apiService.categories()
            emit(ProductApiState.success(categories))
        }.flowOn(Dispatchers.IO)
    }
}