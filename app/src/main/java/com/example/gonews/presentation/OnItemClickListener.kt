package com.example.gonews.presentation

import com.example.gonews.model.news.Article

interface OnItemClickListener {
    fun onItemClick(article: Article?)
}