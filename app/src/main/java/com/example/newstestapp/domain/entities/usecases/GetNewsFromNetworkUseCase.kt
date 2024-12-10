package com.example.newstestapp.domain.entities.usecases

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.entities.repositories.NewsRepository

data class GetNewsFromNetworkUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(categoriesEnum: CategoriesEnum) =
        newsRepository.getNewsFromNetwork(categoriesEnum)
}
