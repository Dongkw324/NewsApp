package com.kdw.newsapp.repository

import com.kdw.newsapp.api.NewsApi
import com.kdw.newsapp.apiCall.NewsApiCall
import com.kdw.newsapp.model.Article
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val newsApi: NewsApi
): NewsApiCall() {

    suspend fun getBreakingArticles(): MutableList<Article>? {
        return safeApiCall(
            call = { newsApi.getArticles() },
            exception = "Error Fetching Articles"
        )?.articles?.toMutableList()
    }

    suspend fun getSearchingArticles(query: String) : MutableList<Article>? {
        return safeApiCall(
            call = { newsApi.searchArticles(query) },
            exception = "Error Fetching Searched Articles"
        )?.articles?.toMutableList()
    }

}