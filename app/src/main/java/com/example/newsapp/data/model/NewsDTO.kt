package com.example.newsapp.data.model

data class NewsDTO(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)