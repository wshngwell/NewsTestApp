package com.example.newstestapp.data.remoteDataSource.dto

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    val newsList:List<Article>
)