package com.example.newsapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.model.Article
import com.example.newsapp.databinding.ItemNewsBinding

class NewsListAdapter(
    private val news: List<Article>
) : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    class NewsListViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val currentNews = news[position]
        holder.binding.apply {
            tvTitle.text = currentNews.title
            tvDesc.text = currentNews.description
            Glide.with(root.context)
                .load(currentNews.urlToImage)
                .into(ivNews)

        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

}