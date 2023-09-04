package com.example.gonews.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val BASE_URL = "https://newsapi.org/v2/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}