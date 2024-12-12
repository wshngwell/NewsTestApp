package com.example.newstestapp.data.localStorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newstestapp.data.localStorage.dbModels.NewsDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_table")
    fun getNewsFromDb(): Flow<List<NewsDbModel>>

    @Query("SELECT EXISTS(SELECT * FROM news_table WHERE id =:newsId)")
    fun isNewsFavourite(newsId:Int): Flow<Boolean>

    @Insert
    suspend fun addCityToDb(newsDbModel: NewsDbModel)

    @Query("DELETE FROM news_table WHERE id =:newsId")
    suspend fun removeFromDb(newsId:Int)
}