package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdw.newsapp.R
import com.kdw.newsapp.adapter.ArticleAdapter
import com.kdw.newsapp.databinding.FragmentSearchArticleBinding
import com.kdw.newsapp.viewModel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchArticleFragment: Fragment(R.layout.fragment_search_article) {


    private var _searchBinding : FragmentSearchArticleBinding? = null
    private val searchBinding get() = _searchBinding!!

    private lateinit var searchArticleAdapter: ArticleAdapter
    private val articleViewModel: ArticleViewModel by viewModels()

    private var job: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _searchBinding = FragmentSearchArticleBinding.inflate(inflater, container, false)
        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchArticleAdapter = ArticleAdapter()

        searchNews()

        searchBinding.searchArticleRecyclerview.apply {
            adapter = searchArticleAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        articleViewModel.searchedArticleLiveData.observe(viewLifecycleOwner, {
            searchArticleAdapter.differ.submitList(it)
        })

        seeSearchedNews()
    }

    private fun searchNews() {
        searchBinding.searchingKeyWord.addTextChangedListener { edit ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                edit?.let {
                    if(edit.toString().isNotEmpty()) {
                        articleViewModel.getSearchingNews(edit.toString())
                    }
                }
            }
        }
    }

    private fun seeSearchedNews() {
        searchArticleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            findNavController().navigate(
                R.id.action_searchArticleFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        _searchBinding = null
        super.onDestroyView()
    }


}