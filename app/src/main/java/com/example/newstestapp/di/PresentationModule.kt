package com.example.newstestapp.di

import androidx.lifecycle.ViewModel
import com.example.newstestapp.data.NewsRepositoryImpl
import com.example.newstestapp.domain.repositories.NewsRepository
import com.example.newstestapp.presentation.bookMarksScreen.BookmarksViewModel
import com.example.newstestapp.presentation.newsScreen.NewsScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface PresentationModule {

    @ApplicationScope
    @Binds
    fun bindsNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    @IntoMap
    @ViewModelKey(BookmarksViewModel::class)
    fun bindsBookmarksViewModule(bookmarksViewModel: BookmarksViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(NewsScreenViewModel::class)
    fun bindsNewsScreenViewModule(newsScreenViewModel: NewsScreenViewModel): ViewModel
}