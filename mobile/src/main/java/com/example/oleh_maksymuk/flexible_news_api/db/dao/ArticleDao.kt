package com.example.oleh_maksymuk.flexible_news_api.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.oleh_maksymuk.flexible_news_api.service.model.Article

@Dao
abstract class ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveArticle(article: Article?): Long

    @Query("SELECT * from article")
    abstract fun getArticles(): List<Article>

    @Query("SELECT * FROM article WHERE pk in (:pk)")
    abstract fun findArticleByPk(pk: Long): Article

    fun saveArticleAndGet(article: Article?): Article {
        val pk = saveArticle(article)
        return findArticleByPk(pk)
    }
}