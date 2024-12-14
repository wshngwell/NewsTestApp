package com.example.newstestapp.presentation.detailedScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.domain.usecases.AddNewsToFavouriteUseCase
import com.example.newstestapp.domain.usecases.CheckIsNewsFavouriteUseCase
import com.example.newstestapp.domain.usecases.RemoveNewsFromFavouriteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedScreenViewModel @Inject constructor(
    private val addNewsToFavouriteUseCase: AddNewsToFavouriteUseCase,
    private val checkIsNewsFavouriteUseCase: CheckIsNewsFavouriteUseCase,
    private val removeNewsFromFavouriteUseCase: RemoveNewsFromFavouriteUseCase,
    private val title: String
) : ViewModel() {

    val isNewsInDb = checkIsNewsFavouriteUseCase(title)
        .map {
            Log.d("DetailedScreenViewModel", "$it")
            if (it) {
                IsFavouriteState.Favourite
            } else {
                IsFavouriteState.NotFavourite
            }

        }
        .onStart { IsFavouriteState.Loading }
        .catch { IsFavouriteState.Error }
        .stateIn(
            scope = viewModelScope,
            SharingStarted.Eagerly,
            initialValue = IsFavouriteState.Loading
        )

    fun isElementInDB() = isNewsInDb


    fun addNewsToDb(news: News) =
        viewModelScope.launch {
            addNewsToFavouriteUseCase(news)
        }

    fun removeNewsFromDb(title: String) =
        viewModelScope.launch {
            removeNewsFromFavouriteUseCase(title)
        }


}