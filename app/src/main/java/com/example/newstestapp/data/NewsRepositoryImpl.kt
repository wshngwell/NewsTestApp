package com.example.newstestapp.data

import android.util.Log
import com.example.newstestapp.data.localStorage.NewsDao
import com.example.newstestapp.data.remoteDataSource.ApiService
import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.domain.repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao,
) : NewsRepository {


    private val reactOnEventFlow = MutableSharedFlow<Unit>(replay = 1)
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    override fun getNewsFromNetwork(category: CategoriesEnum): Flow<List<News>> = flow {
            Log.d("NewsRepositoryImpl", "COMPOSITION")
            val newsResponseDto = apiService.getNewsList(category.category)
            val newsList = mapNewsResponseDtoToNews(newsResponseDto)
            emit(newsList)
    }

    override fun getNewsListFromLocalStorage(): Flow<List<News>> {
        return newsDao.getNewsFromDb().map {
            mapNewsListFromDbToNews(it)
        }
    }

    override fun isNewsFavourite(newsId: Int): Flow<Boolean> = newsDao.isNewsFavourite(newsId)

    override suspend fun addNewsToFavourite(news: News) =
        newsDao.addCityToDb(mapToNewsToNewsListFromDb(news))

    override suspend fun removeNewsFromFavourite(newsId: Int) = newsDao.removeFromDb(newsId)
}