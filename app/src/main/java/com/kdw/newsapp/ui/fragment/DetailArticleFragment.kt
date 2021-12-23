package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kdw.newsapp.R
import com.kdw.newsapp.databinding.FragmentDetailArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailArticleFragment: Fragment(R.layout.fragment_detail_article) {

    private var _detailBinding : FragmentDetailArticleBinding? = null
    private val detailBinding get() = _detailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _detailBinding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        return detailBinding.root
    }

    override fun onDestroyView() {
        _detailBinding = null
        super.onDestroyView()
    }
}