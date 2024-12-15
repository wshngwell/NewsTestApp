package com.example.newstestapp.data

import com.example.newstestapp.data.localStorage.NewsDao
import com.example.newstestapp.data.remoteDataSource.ApiService
import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.domain.entities.NewsResult
import com.example.newstestapp.domain.repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao,
) : NewsRepository {


    private val reactOnEventInDbFlow = MutableSharedFlow<Unit>(replay = 1)

    private val reactOnEventInLoadBusinessNewsFromNetworkFlow = MutableSharedFlow<Unit>(replay = 1)
    private val reactOnEventInLoadHealthNewsFromNetworkFlow = MutableSharedFlow<Unit>(replay = 1)
    private val reactOnEventInLoadSportNewsFromNetworkFlow = MutableSharedFlow<Unit>(replay = 1)
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _businessNewsList = mutableListOf<News>()
    private val _healthNewsList = mutableListOf<News>()
    private val _sportNewsList = mutableListOf<News>()

    private val businessNewsList: List<News>
        get() = _businessNewsList.toList()
    private val healthNewsList: List<News>
        get() = _healthNewsList.toList()
    private val sportNewsList: List<News>
        get() = _sportNewsList.toList()

    private var businessCurrentPage = 1
    private var healthCurrentPage = 1
    private var sportCurrentPage = 1

    private val businessNewsFlow by lazy {
        flow {
            reactOnEventInLoadBusinessNewsFromNetworkFlow.emit(Unit)

            reactOnEventInLoadBusinessNewsFromNetworkFlow.collect {
                val newsResponseDto =
                    apiService.getNewsList(
                        CategoriesEnum.BUSINESS.category,
                        page = businessCurrentPage
                    )

                if (newsResponseDto.totalResult == _businessNewsList.size) {
                    return@collect
                }
                val newsListFromNet = mapNewsResponseDtoToNews(newsResponseDto)

                _businessNewsList.addAll(newsListFromNet)
                businessCurrentPage++
                emit(NewsResult.Success(businessNewsList) as NewsResult)
            }
        }
            .retry(2) {
                delay(DELAY_OF_RETRYING)
                true
            }
            .catch { emit(NewsResult.Error) }
            .stateIn(
                scope = coroutineScope,
                SharingStarted.Eagerly,
                NewsResult.Initial as NewsResult
            )
    }

    private val healthNewsFlow by lazy {
        flow {
            reactOnEventInLoadHealthNewsFromNetworkFlow.emit(Unit)

            reactOnEventInLoadHealthNewsFromNetworkFlow.collect {

                val newsResponseDto =
                    apiService.getNewsList(CategoriesEnum.HEALTH.category, page = healthCurrentPage)

                if (newsResponseDto.totalResult == _healthNewsList.size) {
                    return@collect
                }
                val newsListFromNet = mapNewsResponseDtoToNews(newsResponseDto)

                _healthNewsList.addAll(newsListFromNet)
                healthCurrentPage++
                emit(NewsResult.Success(healthNewsList) as NewsResult)
            }
        }.retry(2) {
            delay(DELAY_OF_RETRYING)
            true
        }
            .catch { (emit(NewsResult.Error as NewsResult)) }
            .stateIn(
                scope = coroutineScope,
                SharingStarted.Eagerly,
                NewsResult.Initial as NewsResult
            )
    }
    private val sportNewsFlow by lazy{
        flow {
            reactOnEventInLoadSportNewsFromNetworkFlow.emit(Unit)

            reactOnEventInLoadSportNewsFromNetworkFlow.collect {
                val newsResponseDto =
                    apiService.getNewsList(CategoriesEnum.SPORTS.category, page = sportCurrentPage)

                if (newsResponseDto.totalResult == _sportNewsList.size) {
                    return@collect
                }
                val newsListFromNet = mapNewsResponseDtoToNews(newsResponseDto)

                _sportNewsList.addAll(newsListFromNet)
                sportCurrentPage++
                emit(NewsResult.Success(sportNewsList) as NewsResult)
            }
        }
            .retry(2) {
                delay(DELAY_OF_RETRYING)
                true
            }
            .catch { emit(NewsResult.Error as NewsResult) }
            .stateIn(
                scope = coroutineScope,
                SharingStarted.Eagerly,
                NewsResult.Initial as NewsResult
            )
    }

    override fun getBusinessNewsFromNetwork() = businessNewsFlow

    override fun getHealthNewsFromNetwork() = healthNewsFlow

    override fun getSportNewsFromNetwork() = sportNewsFlow

    override suspend fun loadNextNews(category: CategoriesEnum) {
        when (category) {
            CategoriesEnum.BUSINESS -> reactOnEventInLoadBusinessNewsFromNetworkFlow.emit(Unit)
            CategoriesEnum.HEALTH -> reactOnEventInLoadHealthNewsFromNetworkFlow.emit(Unit)
            CategoriesEnum.SPORTS -> reactOnEventInLoadSportNewsFromNetworkFlow.emit(Unit)
        }
    }

    override fun getNewsListFromLocalStorage(): Flow<List<News>> {
        return newsDao.getNewsFromDb()
            .map {
                mapNewsListFromDbToNews(it)
            }
    }

    override fun isNewsFavourite(title: String): StateFlow<Boolean> = flow {
        reactOnEventInDbFlow.emit(Unit)
        reactOnEventInDbFlow.collect {

            emit(newsDao.isNewsFavourite(title))
        }
    }.stateIn(
        scope = coroutineScope,
        SharingStarted.Eagerly,
        false
    )


    override suspend fun addNewsToFavourite(news: News) {
        newsDao.addCityToDb(mapToNewsToNewsListFromDb(news))
        reactOnEventInDbFlow.emit(Unit)
    }


    override suspend fun removeNewsFromFavourite(title: String) {
        newsDao.removeFromDb(title)
        reactOnEventInDbFlow.emit(Unit)
    }

    companion object {
        private const val DELAY_OF_RETRYING = 15000L
    }
}
