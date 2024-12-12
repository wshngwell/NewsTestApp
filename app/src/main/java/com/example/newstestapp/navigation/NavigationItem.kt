package com.example.newstestapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newstestapp.R


sealed class NavigationItem(
    val resStringId: Int,
    val icon: ImageVector,
    val screen: Screen
)

data object Home : NavigationItem(
    resStringId = R.string.news,
    icon = Icons.Outlined.Home,
    screen = Screen.HomeScreen
)

data object Favourite : NavigationItem(
    resStringId = R.string.bookmarks,
    icon = Icons.Outlined.Favorite,
    screen = Screen.Bookmarks
)
