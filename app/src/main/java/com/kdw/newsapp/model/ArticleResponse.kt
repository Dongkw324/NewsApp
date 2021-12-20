package com.kdw.newsapp.model


import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)