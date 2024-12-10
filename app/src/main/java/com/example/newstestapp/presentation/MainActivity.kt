package com.example.newstestapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.newstestapp.data.remoteDataSource.ApiFactory
import com.example.newstestapp.presentation.ui.theme.NewsTestAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch{
            ApiFactory.apiService.getNewsList(category = "sports")
        }

        setContent {
            NewsTestAppTheme {

            }
        }
    }
}