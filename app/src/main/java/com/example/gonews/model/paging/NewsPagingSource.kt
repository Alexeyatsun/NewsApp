package com.example.gonews.model.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gonews.data.retrofit.ApiService
import com.example.gonews.model.news.Article
import retrofit2.HttpException


class NewsPagingSource(
    private val api: ApiService,
    private val newsName: String,
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page: Int = params.key ?: 1
        val pageSize = 100
        return try {

            val response = api.getNews(newsName, pageSize = pageSize, page = page)
            val articles = elementFilter(checkNotNull(response.body()?.articles))

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun elementFilter(articles: List<Article>): List<Article> {
        val filterArticle = mutableListOf<Article>()
        articles.forEach {
            if (it.title != "[Removed]"&&it.title!=null) {
                filterArticle.add(it)
            }
        }
        return filterArticle
    }
}