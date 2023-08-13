package com.charlesmuchogo.amazone

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import com.charlesmuchogo.amazone.data.models.BottomNavigationItem
import com.charlesmuchogo.amazone.presentation.home.Homepage
import com.charlesmuchogo.amazone.presentation.viewModel.ProductsViewModel
import com.charlesmuchogo.amazone.ui.theme.AmazoneTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazoneTheme {

                val viewModel =  hiltViewModel<ProductsViewModel>()
                DestinationsNavHost(navGraph = NavGraphs.root)

            }
        }
    }
}

@RootNavGraph(start = true)
@Destination
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(navigator: DestinationsNavigator) {
//    var selectedItem by rememberSaveable {
//        mutableSetOf(0)
//    }
    var items = listOf<BottomNavigationItem>(
        BottomNavigationItem(
            screen = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
        ),  BottomNavigationItem(
            screen = "Favourites",
            selectedIcon = Icons.Filled.Favorite,
            unSelectedIcon = Icons.Outlined.FavoriteBorder,
        ),  BottomNavigationItem(
            screen = "Cart",
            selectedIcon = Icons.Filled.ShoppingCart,
            unSelectedIcon = Icons.Outlined.ShoppingCart,
        ),
        BottomNavigationItem(
            screen = "Account",
            selectedIcon = Icons.Filled.Person,
            unSelectedIcon = Icons.Outlined.Person,
        )
    )
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed{index, item -> NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Text(text = item.screen)},
                    icon = { Icon(imageVector = item.selectedIcon, contentDescription = item.screen ) }) }
            }

        }
    ){
        Homepage(navigator= navigator)
    }


}



