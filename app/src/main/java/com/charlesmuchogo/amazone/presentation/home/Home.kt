package com.charlesmuchogo.amazone.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.charlesmuchogo.amazone.presentation.viewModel.ProductsViewModel
import com.example.amazone.utils.enums.ApiStatus
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(navigator: DestinationsNavigator, ) {
    val viewModel =  hiltViewModel<ProductsViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                    )
                },
            )
        },
    ) {
        val productState = viewModel.productState.collectAsState()
        val categoriesState = viewModel.categoriesState.collectAsState()
        when (productState.value.status) {
            ApiStatus.LOADING -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }


            ApiStatus.SUCCESS -> {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Products loaded successfully")
                }


//                productState.value.data?.let { products ->
//                    LazyColumn(
//                        verticalArrangement = Arrangement.Top,
//                    ) {item { Spacer(modifier = Modifier.height(15.dp)) }
//                        item {            Text(
//                            text = "Popular Products:",
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White,
//                            modifier = Modifier
//                                .padding(horizontal = 16.dp),
//                            textAlign = TextAlign.Center,
//                            fontSize = 20.sp,
//                        ) }
//                        item {   ProductList(products.products)
//                        }
//                        item {
//
//                            Text(
//                                text = "Categories:",
//                                fontWeight = FontWeight.Bold,
//                                color = Color.White,
//                                modifier = Modifier
//                                    .padding(horizontal = 16.dp),
//                                textAlign = TextAlign.Center,
//                                fontSize = 20.sp
//                            )
//                        }
//                        item {
//                            categoriesState.value.data?.let { categories ->
//                                CategoriesList(categories.product_categories)
//                            }
//                        }
//
//                    }
//                }
            }

            ApiStatus.ERROR -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Failed to load Products \uD83D\uDE14")
                }
            }
        }
    }
}