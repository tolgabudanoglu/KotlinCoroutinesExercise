package com.example.androidcoroutinesflow.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoroutinesflow.R
import com.example.androidcoroutinesflow.databinding.ItemNewsArticleBinding
import com.example.androidcoroutinesflow.model.NewsArticle

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    private val newsItems = arrayListOf<NewsArticle>()

    fun onAddNewsItem(item: NewsArticle) {
        newsItems.add(0, item)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder.from(parent)
    }
    override fun getItemCount() = newsItems.size

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(newsItems[position])
    }

    class NewsItemViewHolder(private val binding:ItemNewsArticleBinding): RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.newsImage
        private val author = binding.newsAuthor
        private val title = binding.newsTitle
        private val publishedAt = binding.newsPublishedAt

        fun bind(newsItem: NewsArticle) {
            imageView.loadImage(newsItem.urlToImage)
            author.text = newsItem.author
            title.text = newsItem.title
            publishedAt.text = newsItem.publishedAt
        }
        companion object{
            fun from(parent: ViewGroup): NewsItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNewsArticleBinding.inflate(layoutInflater, parent, false)
                return NewsItemViewHolder(binding)
            }
        }
    }

}