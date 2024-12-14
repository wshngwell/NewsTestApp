package com.example.newstestapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.newstestapp.domain.entities.News

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    openFavouriteNewsScreen: @Composable () -> Unit,
    openNewsScreen: @Composable () -> Unit,
    openDetailedScreen: @Composable (News) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {

        NewsNavGraph(
            openNewsScreen = openNewsScreen,
            openDetailedScreen = openDetailedScreen
        )
        BookmarksNavGraph(
            openFavouriteNewsScreen = openFavouriteNewsScreen,
            openDetailedScreen = openDetailedScreen
        )


    }
}