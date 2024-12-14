package com.example.newstestapp.navigation

import android.net.Uri
import com.example.newstestapp.domain.entities.News
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {
    object HomeScreen : Screen(
        route = HOME_SCREEN
    )

    object NewsScreen : Screen(
        route = NEWS_SCREEN
    )

    object Bookmarks : Screen(
        route = BOOKMARKS_SCREEN
    )

    object FavouriteNewsScreen : Screen(
        route = FAVOURITE_NEWS_SCREEN
    )

    object DetailedFromAllNewsScreen : Screen(
        route = DETAILED_ALL_NEWS_SCREEN_WITH_ARGS
    ) {
        fun getDetailedScreenWithArgs(news: News): String {
            val gsonNews = Uri.encode(Gson().toJson(news))
            return DETAILED_FROM_ALL_NEWS_SCREEN + "/${gsonNews}"
        }
    }

    object DetailedFavouriteNewsScreen : Screen(
        route = DETAILED_FAVOURITE_NEWS_SCREEN_WITH_ARGS
    ) {
        fun getDetailedScreenWithArgs(news: News): String {
            val gsonNews = Uri.encode(Gson().toJson(news))
            return DETAILED_FROM_FAVOURITE_NEWS_SCREEN + "/${gsonNews}"
        }
    }

    companion object {
        const val HOME_SCREEN = "HOME_SCREEN"


        const val NEWS_SCREEN = "NEWS_SCREEN"
        const val FAVOURITE_NEWS_SCREEN = "FAVOURITE_NEWS_SCREEN"

        const val BOOKMARKS_SCREEN = "BOOKMARKS_SCREEN"
        const val DETAILED_FROM_ALL_NEWS_SCREEN = "DETAILED_FROM_ALL_NEWS_SCREEN"
        const val DETAILED_FROM_FAVOURITE_NEWS_SCREEN = "DETAILED_FROM_FAVOURITE_NEWS_SCREEN"

        const val KEY_NEWS = "key_news"
        const val DETAILED_ALL_NEWS_SCREEN_WITH_ARGS = "DETAILED_FROM_ALL_NEWS_SCREEN/{$KEY_NEWS}"
        const val DETAILED_FAVOURITE_NEWS_SCREEN_WITH_ARGS = "DETAILED_FROM_FAVOURITE_NEWS_SCREEN/{$KEY_NEWS}"
    }
}