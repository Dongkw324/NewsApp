package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdw.newsapp.R
import com.kdw.newsapp.adapter.ArticleAdapter
import com.kdw.newsapp.databinding.FragmentDetailArticleBinding
import com.kdw.newsapp.databinding.FragmentSavedArticleBinding
import com.kdw.newsapp.viewModel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedArticleFragment: Fragment(R.layout.fragment_saved_article) {

    private var _savedBinding : FragmentSavedArticleBinding? = null
    private val savingBinding get() = _savedBinding!!

    private lateinit var savedArticleAdapter: ArticleAdapter
    private val articleViewModel : ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _savedBinding = FragmentSavedArticleBinding.inflate(inflater, container, false)
        return savingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedArticleAdapter = ArticleAdapter()
        savingBinding.savedArticleRecyclerView.apply {
            adapter = savedArticleAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        articleViewModel.getSavedNews().observe(viewLifecycleOwner, {
            savedArticleAdapter.differ.submitList(it)
        })

        seeSavedNews()
    }

    private fun seeSavedNews() {
        savedArticleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            findNavController().navigate(
                R.id.action_savedArticleFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        _savedBinding = null
        super.onDestroyView()
    }
}