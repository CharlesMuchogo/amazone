package com.example.amazone.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.amazone.R
import com.example.amazone.models.Product
import com.example.amazone.presentation.ProductsViewModel
import com.example.amazone.presentation.products.LazyGrid
import com.example.amazone.presentation.products.ProductCard
import com.example.amazone.utils.enums.ApiStatus


@Composable
fun HomeScreen(viewModel: ProductsViewModel) {
    ProductListScreen(viewModel)
}



@Composable
fun ProductListScreen(viewModel: ProductsViewModel) {
    val productState by viewModel.productState.collectAsState()

    when (productState.status) {
        ApiStatus.LOADING -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        ApiStatus.SUCCESS -> {

            productState.data?.let { products ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = "Popular Products:",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    LazyGrid(products.products)

                }

            }
        }

        ApiStatus.ERROR -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Failed to load Products \uD83D\uDE14")
            }        }
    }
}


