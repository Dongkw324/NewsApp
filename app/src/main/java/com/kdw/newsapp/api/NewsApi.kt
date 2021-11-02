package com.kdw.newsapp.api

import com.kdw.newsapp.model.ArticleResponse
import com.kdw.newsapp.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("country")
        countryCode: String = "kr",
        @Query("apiKey")
        apiKey: String = Constant.newsApiKey
    ) : Response<ArticleResponse>

    @GET("v2/everything")
    suspend fun searchArticles(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = Constant.newsApiKey
    ) : Response<ArticleResponse>
}