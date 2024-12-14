package com.example.newstestapp.domain.usecases

import com.example.newstestapp.domain.CategoriesEnum
import com.example.newstestapp.domain.repositories.NewsRepository
import javax.inject.Inject

class LoadNextNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(categoriesEnum: CategoriesEnum) =
        repository.loadNextNews(categoriesEnum)
}