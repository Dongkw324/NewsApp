package com.kdw.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kdw.newsapp.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articleTable")
    fun getArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): Long

    @Delete
    suspend fun deleteArticle(article: Article)

}