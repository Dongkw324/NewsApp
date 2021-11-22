package com.kdw.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdw.newsapp.model.Article
import com.kdw.newsapp.repository.LocalRepository
import com.kdw.newsapp.repository.RemoteRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

class ArticleViewModel @ViewModelScoped constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): ViewModel() {

    private val _articleData = MutableLiveData<MutableList<Article>>()
    private val _searchedArticleData = MutableLiveData<MutableList<Article>>()

    val articleLiveData get() = _articleData
    val searchedArticleLiveData get() = _searchedArticleData

    init {
        getBreakingNews()
    }

    fun getBreakingNews() = viewModelScope.launch {
        val remoteArticles = remoteRepository.getBreakingArticles()
        _articleData.postValue(remoteArticles)
    }

    fun getSearchingNews(query: String) = viewModelScope.launch {
        val searchingArticles = remoteRepository.getSearchingArticles(query)
        _searchedArticleData.postValue(searchingArticles)
    }

    fun savedNews(article: Article) = viewModelScope.launch {
        localRepository.insertArticle(article)
    }

    fun deletedNews(article: Article) = viewModelScope.launch {
        localRepository.deleteArticle(article)
    }

    fun getSavedNews() = localRepository.getArticle()
}