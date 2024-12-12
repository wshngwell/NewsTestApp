package com.example.newstestapp.domain.repositories

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface NewsRepository {

    fun getNewsFromNetwork(category: CategoriesEnum):Flow<List<News>>

    fun getNewsListFromLocalStorage(): Flow<List<News>>
    fun isNewsFavourite(newsId: Int): Flow<Boolean>
   suspend fun addNewsToFavourite(news: News)
  suspend  fun removeNewsFromFavourite(newsId: Int)

}