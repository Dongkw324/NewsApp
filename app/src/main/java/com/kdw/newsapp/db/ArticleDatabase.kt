package com.kdw.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kdw.newsapp.model.Article


@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}