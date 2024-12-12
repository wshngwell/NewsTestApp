package com.example.newstestapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    openBookMarksScreen: @Composable () -> Unit,
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
        composable(Screen.Bookmarks.route) {
            openBookMarksScreen()
        }

    }
}