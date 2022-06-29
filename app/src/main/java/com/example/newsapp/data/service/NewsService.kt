package com.example.newsapp.data.service

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun fetchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsDTO

    @GET("top-headlines")
    suspend fun fetchCategoryNews(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("country") country: String = "tr"
    ) : NewsDTO

}




