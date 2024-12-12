package com.example.newstestapp.presentation.newScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.usecases.GetNewsFromNetworkUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewsScreenViewModel @Inject constructor(
    private val getNewsFromNetworkUseCase: GetNewsFromNetworkUseCase
) : ViewModel() {

    fun loadListOfNews(categoryEnum: CategoriesEnum) = getNewsFromNetworkUseCase(categoryEnum)
        .onStart {
            NewsScreenState.Loading as NewsScreenState
        }
        .map {

            NewsScreenState.LoadedNews(it)

        }
        .catch { NewsScreenState.Error(it.message.toString()) }
}