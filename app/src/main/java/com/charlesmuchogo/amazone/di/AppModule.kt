package com.example.amazone.di

import android.content.Context
import androidx.room.Room
import com.example.amazone.api.ApiService
import com.example.amazone.repository.ProductRepository
import com.example.database.AppDatabase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppRepository(): ApiService{

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val productionUrl = "http://13.246.207.31:8080/api/"
        val localUrl = "http://192.168.135.124:8080/api/"

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder().baseUrl(productionUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context): AppDatabase{
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "amazone.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService, appDatabase:AppDatabase): ProductRepository{
        return  ProductRepository(apiService = apiService, appDb =  appDatabase)
    }
}