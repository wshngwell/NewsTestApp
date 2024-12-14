package com.example.newstestapp.di.subcomponent

import androidx.lifecycle.ViewModel
import com.example.newstestapp.di.ViewModelKey
import com.example.newstestapp.presentation.detailedScreen.DetailedScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailedScreenModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailedScreenViewModel::class)
    fun bindsDetailViewModule(detailedScreenViewModel: DetailedScreenViewModel): ViewModel
}