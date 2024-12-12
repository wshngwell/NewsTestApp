package com.example.newstestapp.domain.entities

data class News(
    val id:Int = ID++,
    val title:String? = null,
    val shortDescr:String? = null,
    val source :String? = null,
    val fullText:String? = null,
    val author:String? = null,
    val dateOfPublication:String? = null,
    val imageUrl:String? = null,
    val url:String? = null,
){
    companion object{
        private var ID:Int = 0
    }
}
