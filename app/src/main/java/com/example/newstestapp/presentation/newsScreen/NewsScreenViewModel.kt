package com.example.newstestapp.presentation.newsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.domain.entities.NewsResult
import com.example.newstestapp.domain.usecases.GetBusinessNewsFromNetworkUseCase
import com.example.newstestapp.domain.usecases.GetHealthNewsFromNetworkUseCase
import com.example.newstestapp.domain.usecases.GetSportNewsFromNetworkUseCase
import com.example.newstestapp.domain.usecases.LoadNextNewsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsScreenViewModel @Inject constructor(
    private val getBusinessNewsFromNetworkUseCase: GetBusinessNewsFromNetworkUseCase,
    private val getHealthNewsFromNetworkUseCase: GetHealthNewsFromNetworkUseCase,
    private val getSportNewsFromNetworkUseCase: GetSportNewsFromNetworkUseCase,
    private val loadNextNewsUseCase: LoadNextNewsUseCase,
) : ViewModel() {

    private val loadNextNews = MutableSharedFlow<NewsScreenState>(replay = 1)

    private var listOfBusinessNews: List<News> = mutableListOf()
    private var listOfHealthNews: List<News> = mutableListOf()
    private var listOfSportNews: List<News> = mutableListOf()

    private var currentCategory = CategoriesEnum.BUSINESS


    fun loadListOfBusinessNews(categoryEnum: CategoriesEnum): Flow<NewsScreenState> {
        currentCategory = categoryEnum
        return getBusinessNewsFromNetworkUseCase()
            .map {
                when (it) {
                    NewsResult.Error -> {
                        NewsScreenState.Error
                    }

                    is NewsResult.Success -> {
                        listOfBusinessNews = it.newsList
                        NewsScreenState.LoadedNews(it.newsList)
                    }

                    NewsResult.Initial -> {
                        NewsScreenState.Loading
                    }
                }

            }
            .onStart {
                NewsScreenState.Loading as NewsScreenState
            }
            .mergeWith(loadNextNews)

    }


    fun loadListOfHealthNews(categoryEnum: CategoriesEnum): Flow<NewsScreenState> {
        currentCategory = categoryEnum
        return getHealthNewsFromNetworkUseCase()

            .onStart {
                NewsScreenState.Loading
            }
            .map {
                when (it) {

                    NewsResult.Error -> NewsScreenState.Error
                    is NewsResult.Success -> {
                        listOfHealthNews = it.newsList
                        NewsScreenState.LoadedNews(it.newsList)
                    }

                    NewsResult.Initial -> NewsScreenState.Loading
                }
            }
            .mergeWith(loadNextNews)
    }


    fun loadListOfSportNews(categoryEnum: CategoriesEnum): Flow<NewsScreenState> {
        currentCategory = categoryEnum
        return getSportNewsFromNetworkUseCase()

            .onStart {
                NewsScreenState.Loading
            }
            .map {
                when (it) {
                    NewsResult.Error -> NewsScreenState.Error
                    is NewsResult.Success -> {
                        listOfSportNews = it.newsList
                        NewsScreenState.LoadedNews(it.newsList)
                    }

                    NewsResult.Initial -> NewsScreenState.Loading
                }
            }
            .mergeWith(loadNextNews)
    }


    fun loadMoreNews() {
        viewModelScope.launch {
            loadNextNewsUseCase(currentCategory)
        }
    }


}