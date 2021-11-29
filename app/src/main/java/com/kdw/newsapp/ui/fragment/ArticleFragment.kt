package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.kdw.newsapp.R
import com.kdw.newsapp.adapter.ArticleAdapter
import com.kdw.newsapp.databinding.FragmentArticleBinding
import com.kdw.newsapp.viewModel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var articleAdapter: ArticleAdapter
    private val viewModel: ArticleViewModel by viewModels()

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

}