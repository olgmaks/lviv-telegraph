package com.example.oleh_maksymuk.flexible_news_api.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oleh_maksymuk.flexible_news_api.R
import com.example.oleh_maksymuk.flexible_news_api.service.model.Article
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArticleListAdapter(private val articles: List<Article>, private var parentActivity: Activity) :
    RecyclerView.Adapter<ArticleListAdapter.ArticleListAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ArticleListAdapterViewHolder(itemView)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleListAdapterViewHolder, position: Int) {
        val article = articles[position]
        holder.articleTitle.text = article.title
        holder.articleDescription.text = article.description
        holder.followLink.text = article.source.name
        Picasso.get().load(Uri.parse(article.urlToImage)).into(holder.articleImage)
        holder.followLink.setOnClickListener { openBrowserLink(article) }
        holder.articlePublishDate.text = getPublishedAtAsString(article)
    }

    private fun getPublishedAtAsString(article: Article): String {
//        val date = LocalDateTime.parse(article.publishedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
//        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ", " + date.format(DateTimeFormatter.ofPattern("HH:mm"))
        return article.publishedAt
    }

    private fun openBrowserLink(article: Article) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
        parentActivity.startActivity(intent)
    }


    class ArticleListAdapterViewHolder(
        itemView: View,
        val articleTitle: TextView = itemView.findViewById(R.id.article_title),
        val articleDescription: TextView = itemView.findViewById(R.id.article_description),
        val articleImage: ImageView = itemView.findViewById(R.id.article_image),
        val followLink: Button = itemView.findViewById(R.id.follow_article_link),
        val articlePublishDate:TextView = itemView.findViewById(R.id.article_published_date)
    ) : RecyclerView.ViewHolder(itemView)


}