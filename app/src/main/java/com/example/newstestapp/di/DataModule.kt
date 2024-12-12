package com.example.newstestapp.di

import android.content.Context
import com.example.newstestapp.data.localStorage.NewsDao
import com.example.newstestapp.data.localStorage.NewsDatabase
import com.example.newstestapp.data.remoteDataSource.ApiFactory
import com.example.newstestapp.data.remoteDataSource.ApiService
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    companion object{

        @ApplicationScope
        @Provides
        fun providesApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun providesNewsDao(context: Context): NewsDao = NewsDatabase.getInstance(context).getNewsDao()
    }

}