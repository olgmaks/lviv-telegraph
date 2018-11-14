package com.example.oleh_maksymuk.flexible_news_api.service.model

data class GetTopLinesResponse(
    val status: String ,
    val totalResults: Int,
    val articles: List<Article>
)