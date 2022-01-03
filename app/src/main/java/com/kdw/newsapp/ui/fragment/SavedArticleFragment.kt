package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kdw.newsapp.R
import com.kdw.newsapp.databinding.FragmentDetailArticleBinding
import com.kdw.newsapp.databinding.FragmentSavedArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedArticleFragment: Fragment(R.layout.fragment_saved_article) {

    private var _savedBinding : FragmentSavedArticleBinding? = null
    private val savingBinding get() = _savedBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _savedBinding = FragmentSavedArticleBinding.inflate(inflater, container, false)
        return savingBinding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}