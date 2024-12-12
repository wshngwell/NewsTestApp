package com.example.newstestapp.data.remoteDataSource

import com.example.newstestapp.data.remoteDataSource.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsList(
        @Query("category") category: String,
    ):NewsResponseDto

}