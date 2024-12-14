package com.example.newstestapp.domain.entities

sealed class NewsResult {

    data object Initial:NewsResult()
    data object Error : NewsResult()
    data class Success(val newsList: List<News>) : NewsResult()
}