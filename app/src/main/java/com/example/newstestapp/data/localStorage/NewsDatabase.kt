package com.example.newstestapp.data.localStorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newstestapp.data.localStorage.dbModels.NewsDbModel

@Database(entities = [NewsDbModel::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        private const val DB_NAME = "NewsDb"

        private var INSTANCE: NewsDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): NewsDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = NewsDatabase::class.java,
                    name = DB_NAME
                )
                    .build()

                INSTANCE = database
                return database

            }

        }
    }
}