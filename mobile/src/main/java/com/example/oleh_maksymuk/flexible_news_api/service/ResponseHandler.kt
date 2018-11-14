package com.example.oleh_maksymuk.flexible_news_api.service

interface ResponseHandler<in T> {
        fun onResult(result: T)
    }