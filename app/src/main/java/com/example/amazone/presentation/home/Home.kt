package com.example.amazone.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.amazone.R
import com.example.amazone.models.Category
import com.example.amazone.models.Product
import com.example.amazone.presentation.ProductsViewModel
import com.example.amazone.presentation.products.LazyGrid
import com.example.amazone.presentation.products.ProductCard
import com.example.amazone.presentation.products.ProductList
import com.example.amazone.utils.enums.ApiStatus


@Composable
fun HomeScreen(viewModel: ProductsViewModel) {
    ProductListScreen(viewModel)
}


@Composable
fun ProductListScreen(viewModel: ProductsViewModel) {
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
            productState.value.data?.let { products ->
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Popular Products:",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                    ProductList(products.products)

                    Text(
                        text = "Categories:",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )

                    categoriesState.value.data?.let { categories ->
                        CategoriesList(categories.product_categories)
                    }

                    Text(
                        text = "Recently Viewed:",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(horizontal = 16.dp, vertical = 10.dp),
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
            }
        }
    }
}



@Composable
fun CategoriesList(categories: List<Category>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(categories) { category ->
            CategoryCard(category = category)
        }
    }
}

@Composable
fun CategoryCard(category: Category) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .height(150.dp)
            .width(125.dp)
            .wrapContentSize(Alignment.Center)
            .padding(8.dp)

    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly

            ) {
                AsyncImage(
                    model =  category.image,
                    contentDescription = "Category Image",
                    modifier = Modifier
                        .height(90.dp)
                        .width(80.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(40.dp))

                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}


