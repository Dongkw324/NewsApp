package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdw.newsapp.R
import com.kdw.newsapp.adapter.ArticleAdapter
import com.kdw.newsapp.databinding.ActivityMainBinding
import com.kdw.newsapp.databinding.FragmentArticleBinding
import com.kdw.newsapp.viewModel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var articleAdapter: ArticleAdapter
    private val articleViewModel: ArticleViewModel by viewModels()

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleAdapter = ArticleAdapter()
        binding.articleRecyclerView.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        articleViewModel.apply {
            articleLiveData.observe(viewLifecycleOwner, {
                articleAdapter.differ.submitList(it)
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}