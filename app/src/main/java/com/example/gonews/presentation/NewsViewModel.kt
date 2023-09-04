package com.example.gonews.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.gonews.data.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repositoryImpl:NewsRepositoryImpl
):ViewModel(){

    private val currentQuery = MutableLiveData(newsName)

    val news =  currentQuery.switchMap { queryString ->
        repositoryImpl.getSearchResult(queryString).cachedIn(viewModelScope)
    }

    fun searchNews(query:String){
        currentQuery.value = query
    }
    companion object{
        private const val newsName = "world"
    }
}