package com.example.newstestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newstestapp.domain.entities.News

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateToDetailedScreen(news: News) {
        navHostController.navigate(Screen.Detailed.getDetailedScreenWithArgs(news)) {}
    }
}

@Composable
fun rememberNavigationState(): NavigationState {
    val navHostController = rememberNavController()
    return remember {
        NavigationState(navHostController)
    }
}