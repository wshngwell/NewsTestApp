package com.example.newstestapp.di.subcomponent

import com.example.newstestapp.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DetailedScreenModule::class])
interface DetailedNewsComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance title: String): DetailedNewsComponent
    }
}