package com.charlesmuchogo.amazone.data.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val screen: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)
