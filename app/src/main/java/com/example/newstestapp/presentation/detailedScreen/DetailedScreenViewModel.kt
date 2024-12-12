package com.example.newstestapp.presentation.detailedScreen

import androidx.lifecycle.ViewModel
import com.example.newstestapp.domain.usecases.AddNewsToFavouriteUseCase
import com.example.newstestapp.domain.usecases.CheckIsNewsFavouriteUseCase
import com.example.newstestapp.domain.usecases.RemoveNewsFromFavouriteUseCase
import javax.inject.Inject

class DetailedScreenViewModel @Inject constructor(
    private val addNewsToFavouriteUseCase: AddNewsToFavouriteUseCase,
    private val checkIsNewsFavouriteUseCase: CheckIsNewsFavouriteUseCase,
    private val removeNewsFromFavouriteUseCase: RemoveNewsFromFavouriteUseCase
) : ViewModel() {


}