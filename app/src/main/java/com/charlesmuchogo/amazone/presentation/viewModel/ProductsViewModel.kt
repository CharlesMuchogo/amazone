package com.charlesmuchogo.amazone.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.amazone.api.ApiService
import com.example.amazone.api.ProductApiState
import com.example.amazone.models.CategoriesDTO
import com.example.amazone.models.Products
import com.example.amazone.repository.ProductRepository
import com.example.amazone.utils.enums.ApiStatus
import com.example.database.AppDatabase
import com.example.database.ProductDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val appDatabase: AppDatabase,
    private  val apiService: ApiService
) :ViewModel() {
    private  val repository = ProductRepository(apiService, appDatabase)
    val productState = MutableStateFlow(
        ProductApiState<Products>(
            status = ApiStatus.LOADING,
            null,
            "Getting Products"
        )
    )

    val categoriesState = MutableStateFlow(
        ProductApiState<CategoriesDTO>(
            status = ApiStatus.LOADING,
            null,
            "Getting Products"
        )
    )

    init {
        getProducts()
        getCategories()
    }

    private fun getProducts(){
        productState.value = ProductApiState.loading()
        viewModelScope.launch {
            repository.getProducts()
                .catch {
                    productState.value =  ProductApiState.error(it.message.toString())
                }
                .collect{
                    productState.value =  ProductApiState.success(it.data)
                    appDatabase.withTransaction {
                        var products: MutableList<com.example.database.Product> = ArrayList()
                        for (item in productState.value.data?.products!!) {
                           val product = com.example.database.Product(
                               name = item.name,
                               category =  item.category,
                               id = item.id,
                               image = item.image,
                               price = item.price,
                               quantity = item.quantity,
                               serial_number = item.serial_number
                           )
                            products.add(product)
                        }
                        appDatabase.productDao().upsertAll(products = products)
                    }
                }
        }
    }


    private fun getCategories(){
        categoriesState.value = ProductApiState.loading()

        viewModelScope.launch {
            repository.getCategories()
                .catch {
                    categoriesState.value =  ProductApiState.error(it.message.toString())
                }
                .collect{
                    categoriesState.value =  ProductApiState.success(it.data)
                }
        }
    }

}