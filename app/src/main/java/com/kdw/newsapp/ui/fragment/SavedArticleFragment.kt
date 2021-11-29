package com.kdw.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kdw.newsapp.R
import com.kdw.newsapp.databinding.FragmentSavedArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedArticleFragment: Fragment(R.layout.fragment_saved_article) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSavedArticleBinding.bind(view)
    }

}