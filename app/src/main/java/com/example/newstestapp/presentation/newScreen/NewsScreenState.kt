package com.example.newstestapp.presentation.newScreen

import com.example.newstestapp.domain.entities.News

sealed class NewsScreenState {

    data object Initial : NewsScreenState()
    data object Loading : NewsScreenState()
    data class LoadedNews(val newsList: List<News>) : NewsScreenState()
    data class Error(val msg: String) : NewsScreenState()
}