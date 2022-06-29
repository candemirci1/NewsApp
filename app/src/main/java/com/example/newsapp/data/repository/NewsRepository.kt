package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsDTO
import com.example.newsapp.data.utils.Response
import retrofit2.http.Query

interface NewsRepository {

    suspend fun getNews(query: String): Response<NewsDTO>

    suspend fun getCategoryNews(category: String): Response<NewsDTO>
}