package com.example.oleh_maksymuk.flexible_news_api.db.repository;

import android.os.AsyncTask
import com.example.oleh_maksymuk.flexible_news_api.db.dao.ArticleDao
import com.example.oleh_maksymuk.flexible_news_api.service.model.Article

class ArticlesRepository(private val articleDao: ArticleDao) {


    fun insertArticle(article: Article): AsyncTask<Article, Boolean, Article> {
        return InsertArticleAsync(articleDao).execute(article)
    }

    class InsertArticleAsync(
        private val articleDao: ArticleDao
    ) : AsyncTask<Article, Boolean, Article>() {

        override fun doInBackground(vararg articles: Article?): Article? {
            articles.also {
                return articleDao.saveArticleAndGet(it[0])
            }
        }
    }


}
