package com.example.newstestapp.domain.entities.usecases

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.repositories.NewsRepository

data class GetNewsListInfoFromLocalStorageUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() = newsRepository.getNewsListFromLocalStorage()

}
