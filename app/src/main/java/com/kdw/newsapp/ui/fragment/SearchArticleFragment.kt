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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchArticleBinding.bind(view)
    }
}