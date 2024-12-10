package com.example.newstestapp.data.remoteDataSource.dto

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source")
    val source: Source,
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("urlToImage")
    val imageUrl: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("url")
    val url: String,
)
