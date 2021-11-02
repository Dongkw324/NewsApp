package com.kdw.newsapp.util

import com.kdw.newsapp.BuildConfig

class Constant {
    companion object{
        const val newsApiKey = BuildConfig.NEWS_APIKEY
        const val baseUrl = "https://newsapi.org"
        const val dataBaseName = "article_db.db"
        const val searchArticleTime = 500L
    }
}