package com.example.newstestapp.domain.usecases

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.repositories.NewsRepository
import javax.inject.Inject

data class CheckIsNewsFavouriteUseCase@Inject constructor(
    private val newsRepository: NewsRepository
) {
     operator fun invoke(title:String) = newsRepository.isNewsFavourite(title)

}
