package com.example.oleh_maksymuk.flexible_news_api.service.model

import androidx.room.*

@Entity
data class Article (
    @PrimaryKey(autoGenerate = true) val pk: Long = 0,
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = "",
    @Embedded(prefix = "source") val source: ArticleSource = ArticleSource()
) {}