package com.example.gonews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.gonews.data.retrofit.ApiService
import com.example.gonews.model.paging.NewsPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor (private val api: ApiService){

    fun getSearchResult(newsName:String) =
        Pager(
            config = PagingConfig(
                pageSize = 9,
                prefetchDistance = 6,
                maxSize = 100,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { NewsPagingSource(api,newsName) }
        ).liveData
}