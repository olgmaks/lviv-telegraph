package com.example.oleh_maksymuk.flexible_news_api

import android.content.Context
import android.database.Observable
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.oleh_maksymuk.flexible_news_api.db.AppDB
import com.example.oleh_maksymuk.flexible_news_api.db.dao.AppDatabase
import com.example.oleh_maksymuk.flexible_news_api.db.dao.ArticleDao
import com.example.oleh_maksymuk.flexible_news_api.service.model.Article
import com.example.oleh_maksymuk.flexible_news_api.service.model.ArticleSource
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArticleDaoInstrumentedTest {

    private var appContext: Context = ApplicationProvider.getApplicationContext<Context>()
    private var db: AppDatabase = AppDB.getInstance(appContext)

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val article: Article = Article(
            author = "DEFAULT_AUTHOR",
            title = "DEFAULT_TITLE",
            description = "DEFAULT_DESC",
            url = "https://default.uri.com",
            urlToImage = "https://default.imageuri.com",
            publishedAt = "DEFAULT_PUBLISHED_AT",
            content = "DEFAULT_CONTENT",
            source = ArticleSource(
                id = "DEFAULT_SOURCE_ID",
                name = "DEFAULT_SOURCE_NAME"
            )
        )

        val id = db.articleDao().saveArticleAndGet(article)

        print("")
//        userDao.insert(user)
//        val byName = userDao.findUsersByName("george")
//        assertThat(byName.get(0), equalTo(user))
    }
}