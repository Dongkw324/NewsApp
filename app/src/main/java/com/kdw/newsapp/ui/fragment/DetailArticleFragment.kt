package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kdw.newsapp.R
import com.kdw.newsapp.databinding.FragmentDetailArticleBinding
import com.kdw.newsapp.viewModel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailArticleFragment: Fragment(R.layout.fragment_detail_article) {

    private var _detailBinding : FragmentDetailArticleBinding? = null
    private val detailBinding get() = _detailBinding!!

    private val detailViewModel : ArticleViewModel by viewModels()
    private val args : DetailArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _detailBinding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleView = args.article

        detailBinding.articleWebView.apply {
            webViewClient = WebViewClient()
            articleView.url?.let {
                loadUrl(it)
            }
        }

    }

    override fun onDestroyView() {
        _detailBinding = null
        super.onDestroyView()
    }
}