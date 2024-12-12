package com.example.newstestapp.data.remoteDataSource.dto

import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("articles")
    val newsList:List<ArticleDto>
)