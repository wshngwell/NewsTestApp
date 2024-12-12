package com.example.newstestapp.navigation

import android.net.Uri
import com.example.newstestapp.domain.CategoriesEnum
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

    object Detailed : Screen(
        route = DETAILED_SCREEN_WITH_ARGS
    ) {
        fun getDetailedScreenWithArgs(news: News): String {
            val gsonNews = Uri.encode(Gson().toJson(news))
            return DETAILED_SCREEN + "/${gsonNews}"
        }
    }

    companion object {
        const val HOME_SCREEN = "HOME_SCREEN"


        const val NEWS_SCREEN = "NEWS_SCREEN"

        const val BOOKMARKS_SCREEN = "BOOKMARKS_SCREEN"
        const val DETAILED_SCREEN = "DETAILED_SCREEN"

        const val KEY_NEWS = "key_news"
        const val DETAILED_SCREEN_WITH_ARGS = "DETAILED_SCREEN/{$KEY_NEWS}"
    }
}