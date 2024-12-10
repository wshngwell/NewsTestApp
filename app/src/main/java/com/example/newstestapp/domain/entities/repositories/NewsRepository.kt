package com.example.newstestapp.domain.entities.repositories

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface NewsRepository {

    fun getNewsFromNetwork(category: CategoriesEnum): StateFlow<List<News>>

    fun getNewsListFromLocalStorage(): StateFlow<List<News>>
    fun isNewsFavourite(newsId: Int): StateFlow<Boolean>
    fun addNewsToFavourite(news: News)
    fun removeNewsFromFavourite(newsId: Int)

}