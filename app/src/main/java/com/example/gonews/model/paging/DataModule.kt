package com.example.gonews.model.paging

import com.example.gonews.data.repository.NewsRepositoryImpl
import com.example.gonews.data.retrofit.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun providesNewsRepository(): NewsRepositoryImpl {
        return NewsRepositoryImpl(RetrofitInstance.api)
    }
}