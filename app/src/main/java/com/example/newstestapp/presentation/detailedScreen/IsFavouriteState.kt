package com.example.newstestapp.presentation.detailedScreen

sealed class IsFavouriteState {
    data object Favourite : IsFavouriteState()
    data object NotFavourite : IsFavouriteState()
    data object Loading : IsFavouriteState()
    data object Error : IsFavouriteState()
}