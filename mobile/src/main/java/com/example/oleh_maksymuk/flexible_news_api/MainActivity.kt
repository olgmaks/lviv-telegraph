package com.example.oleh_maksymuk.flexible_news_api

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oleh_maksymuk.flexible_news_api.adapter.ArticleListAdapter
import com.example.oleh_maksymuk.flexible_news_api.constants.IS_PREFERENCES_CHANGED_KEY
import com.example.oleh_maksymuk.flexible_news_api.service.NewApiService
import com.example.oleh_maksymuk.flexible_news_api.service.ResponseHandler
import com.example.oleh_maksymuk.flexible_news_api.service.model.Article
import com.example.oleh_maksymuk.flexible_news_api.service.model.GetTopLinesResponse

class MainActivity : AppCompatActivity() {

    private val newApiService = NewApiService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()

        configureListView()
    }

    private fun configureListView() {
        setProgressBarVisibility(View.VISIBLE)
        newApiService.getTopLines(object : ResponseHandler<GetTopLinesResponse> {
            override fun onResult(result: GetTopLinesResponse) {
                setProgressBarVisibility(View.GONE)
                configureArticlesRecyclerView(result.articles)
            }
        })
    }

    private fun configureArticlesRecyclerView(articles: List<Article>) {
        val recyclerView = findViewById<RecyclerView>(R.id.articles_list)
        val articleListAdapter = ArticleListAdapter(articles, this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = articleListAdapter
    }

    private fun setProgressBarVisibility(visibility: Int) {
        val progressBar = findViewById<ProgressBar>(R.id.article_loading_bar)
        progressBar.visibility = visibility
    }

    override fun onResume() {
        super.onResume()
        if (isPreferencesChanged()) {
            configureListView()
        }
    }

    private fun isPreferencesChanged(): Boolean {
        val defaultSharedPreferences = getDefaultSharedPreferences(this)
        val isPreferencesChanged = defaultSharedPreferences.getBoolean(IS_PREFERENCES_CHANGED_KEY, false)
        defaultSharedPreferences.edit().putBoolean(IS_PREFERENCES_CHANGED_KEY, false).apply()
        return isPreferencesChanged
    }


    private fun configureToolbar() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return if (item.itemId == R.id.action_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
