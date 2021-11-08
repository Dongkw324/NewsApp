package com.kdw.newsapp.di

import android.content.Context
import androidx.room.Room
import com.kdw.newsapp.db.ArticleDatabase
import com.kdw.newsapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDBInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ArticleDatabase::class.java, Constant.dataBaseName).build()

    @Singleton
    @Provides
    fun getArticleDao(db: ArticleDatabase) = db.articleDao()
}