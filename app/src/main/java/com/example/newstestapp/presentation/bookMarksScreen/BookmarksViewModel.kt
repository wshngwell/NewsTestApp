package com.example.newstestapp.presentation.bookMarksScreen

import androidx.lifecycle.ViewModel
import com.example.newstestapp.domain.usecases.GetNewsListInfoFromLocalStorageUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val getNewsListInfoFromLocalStorageUseCase: GetNewsListInfoFromLocalStorageUseCase
) : ViewModel() {

    val favouriteNewsList = getNewsListInfoFromLocalStorageUseCase()
        .map {
            BookmarksScreenState.Loaded(it) as BookmarksScreenState
        }
        .onStart { BookmarksScreenState.Loading as BookmarksScreenState }
        .catch { BookmarksScreenState.Error as BookmarksScreenState }
}