package com.example.newstestapp.data.remoteDataSource

import com.example.newstestapp.data.remoteDataSource.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsList(
        @Query("apiKey") apiKey: String ="4de3851de8ad47a784f840092ad629c8",
        @Query("category") category: String,
    ):NewsResponse

}