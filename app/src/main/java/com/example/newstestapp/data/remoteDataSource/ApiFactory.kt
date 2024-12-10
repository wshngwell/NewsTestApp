package com.example.newstestapp.data.remoteDataSource

import com.example.newstestapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val KEY ="apiKey"

    private const val BASE_URL = "https://newsapi.org/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor { chain->
            val oldRequest = chain.request()
            val newUrl = oldRequest
                .url
                .newBuilder()
                .addQueryParameter(KEY, BuildConfig.API_KEY)
                .build()
            val newRequest = oldRequest
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }.build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)


}