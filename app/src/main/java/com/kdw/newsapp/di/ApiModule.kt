package com.kdw.newsapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kdw.newsapp.BuildConfig
import com.kdw.newsapp.api.NewsApi
import com.kdw.newsapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constant.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit.Builder) : NewsApi = retrofit
            .build()
            .create(NewsApi::class.java)


}