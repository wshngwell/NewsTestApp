package com.example.newstestapp.domain.repositories

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.domain.entities.NewsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface NewsRepository {

    fun getBusinessNewsFromNetwork(): StateFlow<NewsResult>
    fun getHealthNewsFromNetwork(): StateFlow<NewsResult>
    fun getSportNewsFromNetwork(): StateFlow<NewsResult>

    fun getNewsListFromLocalStorage(): Flow<List<News>>
    fun isNewsFavourite(title:String): StateFlow<Boolean>
    suspend fun addNewsToFavourite(news: News)
    suspend fun removeNewsFromFavourite(title: String)
    suspend fun loadNextNews(category: CategoriesEnum)

}