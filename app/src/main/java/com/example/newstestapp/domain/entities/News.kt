package com.example.newstestapp.domain.entities

data class News(
    val id:Int,
    val title:String,
    val shortDescr:String,
    val source :String,
    val fullText:String,
    val author:String,
    val dateOfPublication:String,
    val imageUrl:String,
    val url:String
)
