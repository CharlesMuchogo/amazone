package com.example.amazone.repository

import android.content.Context
import androidx.room.Room
import androidx.room.withTransaction
import com.example.amazone.api.ApiService
import com.example.amazone.api.ProductApiState
import com.example.amazone.models.CategoriesDTO
import com.example.amazone.models.Product
import com.example.amazone.models.Products
import com.example.database.AppDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Module
@InstallIn(SingletonComponent::class)
class ProductRepository(private val apiService: ApiService,private val appDb: AppDatabase) {
    suspend fun getProducts():Flow<ProductApiState<Products>>{
        return  flow {
            var product = apiService.getProducts()
            appDb.withTransaction {
                appDb.productDao().getAll()
            }
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