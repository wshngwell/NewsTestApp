package com.example.newstestapp.di

import android.content.Context
import com.example.newstestapp.di.subcomponent.DetailedNewsComponent
import com.example.newstestapp.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [PresentationModule::class, DataModule::class])
interface ApplicationComponent {

    fun getViewModuleFactory(): ViewModelFactory

    fun createDetailedNewsComponent(): DetailedNewsComponent.Factory


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}