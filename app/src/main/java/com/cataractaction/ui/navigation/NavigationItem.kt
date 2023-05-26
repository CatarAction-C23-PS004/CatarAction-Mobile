package com.cataractaction.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val icon2: ImageVector,
    val screen: Screen
)
