package com.example.newstestapp.domain.entities.usecases

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.domain.entities.repositories.NewsRepository

data class AddNewsToFavouriteUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(news:News) = newsRepository.addNewsToFavourite(news)

}
