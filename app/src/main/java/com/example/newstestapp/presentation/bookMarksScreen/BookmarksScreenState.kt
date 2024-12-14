package com.example.newstestapp.presentation.bookMarksScreen

import com.example.newstestapp.domain.entities.News

sealed class BookmarksScreenState {

    data object Initial : BookmarksScreenState()
    data object Loading : BookmarksScreenState()
    data class Loaded(val newsList:List<News>) : BookmarksScreenState()
    data object Error : BookmarksScreenState()
}