package com.example.oleh_maksymuk.flexible_news_api.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleSource(
    @PrimaryKey(autoGenerate = true) val pk: Long = 0,
    val id: String = "",
    val name: String = ""
)