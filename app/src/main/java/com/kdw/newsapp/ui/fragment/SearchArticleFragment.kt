package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kdw.newsapp.R
import com.kdw.newsapp.databinding.FragmentSearchArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchArticleFragment: Fragment(R.layout.fragment_search_article) {

    private var _searchBinding : FragmentSearchArticleBinding? = null
    private val searchBinding = _searchBinding!!

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


    }

    override fun onDestroyView() {
        _searchBinding = null
        super.onDestroyView()
    }
}