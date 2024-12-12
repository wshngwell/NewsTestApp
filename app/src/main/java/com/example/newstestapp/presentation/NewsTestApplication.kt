package com.example.newstestapp.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.newstestapp.di.ApplicationComponent
import com.example.newstestapp.di.DaggerApplicationComponent

class NewsTestApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("NewsTestApplication", "getApplicationComponent")
    return (LocalContext.current.applicationContext as NewsTestApplication).component
}
