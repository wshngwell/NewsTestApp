package com.example.newstestapp.data.localStorage.dbModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.processing.Generated

@Entity(tableName = "news_table")
data class NewsDbModel(
    @PrimaryKey
    val title:String,
    val shortDescr:String,
    val source :String,
    val fullText:String,
    val author:String,
    val dateOfPublication:String,
    val imageUrl:String,
    val url:String
)