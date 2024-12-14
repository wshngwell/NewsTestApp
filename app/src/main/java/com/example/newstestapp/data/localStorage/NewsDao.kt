package com.example.newstestapp.data.localStorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newstestapp.data.localStorage.dbModels.NewsDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_table")
    fun getNewsFromDb(): Flow<List<NewsDbModel>>

    @Query("SELECT EXISTS(SELECT * FROM news_table WHERE title =:title)")
    fun isNewsFavourite(title:String): Boolean

    @Insert
    suspend fun addCityToDb(newsDbModel: NewsDbModel)

    @Query("DELETE FROM news_table WHERE title =:title")
    suspend fun removeFromDb(title: String)
}