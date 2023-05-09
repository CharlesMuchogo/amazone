package com.example.amazone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazone.api.ApiService
import com.example.amazone.api.ProductApiState
import com.example.amazone.models.Product
import com.example.amazone.models.Products
import com.example.amazone.repository.ProductRepository
import com.example.amazone.utils.enums.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ProductsViewModel @Inject constructor(
    private  val apiService: ApiService
) :ViewModel() {
    private  val repository = ProductRepository(apiService)

    val productState = MutableStateFlow(
        ProductApiState<Products>(
            status = ApiStatus.LOADING,
            null,
            "Getting Products"
        )
    )

    init {
        getProdcts()
    }

    fun getProdcts(){
        productState.value = ProductApiState.loading()

        viewModelScope.launch {
            repository.getProducts()
                .catch {
                    productState.value =  ProductApiState.error(it.message.toString())
                }
                .collect{
                    productState.value =  ProductApiState.success(it.data)
                }

        }
    }

}