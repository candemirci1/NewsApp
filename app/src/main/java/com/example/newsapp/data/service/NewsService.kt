package com.example.newsapp.data.service

import com.example.newsapp.data.model.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun fetchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = myKey
    ): NewsDTO

    @GET("top-headlines")
    suspend fun fetchCategoryNews(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = myKey,
        @Query("country") country: String = "tr"
    ) : NewsDTO

}


val myKey = "991d881e0eae4dc6a80a68842c7579e1"

