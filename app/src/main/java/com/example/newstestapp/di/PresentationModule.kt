package com.example.newstestapp.di

import androidx.lifecycle.ViewModel
import com.example.newstestapp.data.NewsRepositoryImpl
import com.example.newstestapp.domain.repositories.NewsRepository
import com.example.newstestapp.navigation.Screen
import com.example.newstestapp.presentation.detailedScreen.DetailedScreenViewModel
import com.example.newstestapp.presentation.newScreen.NewsScreenViewModel
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
    @ViewModelKey(NewsScreenViewModel::class)
    fun bindsNewsScreenViewModule(newsScreenViewModel: NewsScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailedScreenViewModel::class)
    fun bindsDetailViewModule(detailedScreenViewModel: DetailedScreenViewModel): ViewModel
}