package com.example.oleh_maksymuk.flexible_news_api.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.oleh_maksymuk.flexible_news_api.service.model.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}