package com.example.newstestapp.domain.entities.usecases

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.repositories.NewsRepository

data class CheckIsNewsFavouriteUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(newsId:Int) = newsRepository.isNewsFavourite(newsId)

}
