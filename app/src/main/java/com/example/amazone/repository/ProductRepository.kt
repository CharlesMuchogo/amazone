package com.example.amazone.repository

import com.example.amazone.api.ApiService
import com.example.amazone.api.ProductApiState
import com.example.amazone.models.Product
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Module
@InstallIn(SingletonComponent::class)
class ProductRepository(val productService: ApiService) {
    suspend fun getProducts():Flow<ProductApiState<Product>>{
        return  flow {
            var product = productService.getProducts()
            emit(ProductApiState.success(product))
        }.flowOn(Dispatchers.IO)
    }
}