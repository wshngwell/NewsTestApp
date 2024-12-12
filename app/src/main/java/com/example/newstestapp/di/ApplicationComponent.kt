package com.example.newstestapp.di

import android.content.Context
import com.example.newstestapp.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [PresentationModule::class, DataModule::class])
interface ApplicationComponent {

    fun getViewModuleFactory():ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}