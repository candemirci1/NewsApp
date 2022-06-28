package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsDTO
import com.example.newsapp.data.service.NewsService
import com.example.newsapp.data.utils.Response
import retrofit2.HttpException
import retrofit2.http.Query
import java.io.IOException

class NewsRepositoryImpl(private val service: NewsService): NewsRepository {

    override suspend fun getNews(query: String): Response<NewsDTO> {
        return try {
            val news = service.fetchNews(query)
            Response.Success(news)
        } catch (e: HttpException) {
            Response.Error(e.message.orEmpty(), e.code())
        } catch (e: IOException) {
            Response.Error("check your internet connection")
        }

    }
}