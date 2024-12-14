package com.example.newstestapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.navigation.Screen.Companion.KEY_NEWS
import com.google.gson.Gson

fun NavGraphBuilder.NewsNavGraph(
    openNewsScreen: @Composable () -> Unit,
    openDetailedScreen: @Composable (News) -> Unit,
) {
    navigation(
        startDestination = Screen.NewsScreen.route,
        route = Screen.HomeScreen.route
    ) {
        composable(route = Screen.NewsScreen.route) {
            openNewsScreen()
        }
        composable(
            route = Screen.DetailedFromAllNewsScreen.route,
            arguments = listOf(
                navArgument(KEY_NEWS) {
                    type = NavType.StringType
                }
            )
        ) {
            val jsonNews = it.arguments?.getString(KEY_NEWS) ?: ""

            val news = Gson().fromJson(jsonNews, News::class.java)
            openDetailedScreen(news)
        }
    }
}

fun NavGraphBuilder.BookmarksNavGraph(
    openFavouriteNewsScreen: @Composable () -> Unit,
    openDetailedScreen: @Composable (News) -> Unit,
) {
    navigation(
        startDestination = Screen.FavouriteNewsScreen.route,
        route = Screen.Bookmarks.route
    ) {
        composable(route = Screen.FavouriteNewsScreen.route) {
            openFavouriteNewsScreen()
        }
        composable(
            route = Screen.DetailedFavouriteNewsScreen.route,
            arguments = listOf(
                navArgument(KEY_NEWS) {
                    type = NavType.StringType
                }
            )
        ) {
            val jsonNews = it.arguments?.getString(KEY_NEWS) ?: ""

            val news = Gson().fromJson(jsonNews, News::class.java)
            openDetailedScreen(news)
        }
    }
}