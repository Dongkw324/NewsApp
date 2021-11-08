package com.kdw.newsapp.repository

import com.kdw.newsapp.db.ArticleDao
import com.kdw.newsapp.model.Article
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dao: ArticleDao
) {
    suspend fun insertArticle(article: Article) = dao.insertArticle(article)

    suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)

    fun getArticle() = dao.getArticles()
}