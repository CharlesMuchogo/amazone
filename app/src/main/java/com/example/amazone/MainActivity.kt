package com.example.amazone

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.amazone.presentation.ProductsViewModel
import com.example.amazone.presentation.bottomnavigation.Account
import com.example.amazone.presentation.bottomnavigation.BottomNavItem
import com.example.amazone.presentation.bottomnavigation.Cart
import com.example.amazone.presentation.home.HomeScreen
import com.example.amazone.presentation.products.Products
import com.example.amazone.ui.theme.AmazoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel =  hiltViewModel<ProductsViewModel>()
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar ={BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Products",
                                    route = "products",
                                    icon = Icons.Default.Menu,
                                ),

                                BottomNavItem(
                                    name = "Cart",
                                    route = "cart",
                                    icon = Icons.Default.ShoppingCart,
                                ),
                                BottomNavItem(
                                    name = "Account",
                                    route = "account",
                                    icon = Icons.Default.Person,
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        ) }
                    ) {
                        Navigation(navController = navController, viewModel)


                    }


                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: ProductsViewModel ) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel)
        }
        composable("products") {
            Products(viewModel)
        }
        composable("cart") {
            Cart()
        }
        composable("account") {
            Account()
        }

    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomAppBar(
        modifier = modifier,
        containerColor = Color.DarkGray,
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                label = {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                icon =  {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "${item.name} Icon",
                    )

                }
            )
        }
    }
}

