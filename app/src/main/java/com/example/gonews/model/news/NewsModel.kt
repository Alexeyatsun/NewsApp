package com.example.gonews.model.news

import com.example.gonews.model.news.Article

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)