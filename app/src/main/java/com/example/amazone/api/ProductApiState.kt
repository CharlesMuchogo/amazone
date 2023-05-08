package com.example.amazone.api

import com.example.amazone.utils.enums.ApiStatus


data class ProductApiState<out T>(val status: ApiStatus, val data: List<T>?, val message: String?) {
    companion object{
        // in case of success
        fun <T> success(data: List<T>?):ProductApiState<T>{
            return  ProductApiState(ApiStatus.SUCCESS, data,null)
        }

        //in case of failure
        fun <T>error(msg: String): ProductApiState<T>{
            return ProductApiState(ApiStatus.ERROR, null, msg)
        }
        //loading
        fun <T>loading(): ProductApiState<T>{
            return ProductApiState(ApiStatus.LOADING, null, null)
        }
    }
}