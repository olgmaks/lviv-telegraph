package com.example.oleh_maksymuk.flexible_news_api.db;

import android.content.Context
import androidx.room.Room
import com.example.oleh_maksymuk.flexible_news_api.db.dao.AppDatabase

class AppDB {

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "flex-news.db"
        )
            .build()
    }
}

