package com.example.newstestapp.domain.usecases

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.News
import com.example.newstestapp.domain.repositories.NewsRepository
import javax.inject.Inject

data class AddNewsToFavouriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
  suspend operator fun invoke(news:News) = newsRepository.addNewsToFavourite(news)

}
