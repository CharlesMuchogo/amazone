package com.example.amazone.presentation.bottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.amazone.R

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
