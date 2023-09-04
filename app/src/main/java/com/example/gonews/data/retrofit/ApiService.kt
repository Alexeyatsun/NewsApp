package com.example.gonews.data.retrofit

import androidx.annotation.IntRange
import com.example.gonews.model.news.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("everything?apiKey=e36d7dd1885a4437b55323fafed71f6b&language=en")
    suspend fun getNews(
        @Query("q")name:String? = null,
        @Query("page")@IntRange(from = 1) page:Int = 1,
        @Query("pageSize") pageSize:Int
    ):Response<NewsModel>
}