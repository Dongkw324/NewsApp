package com.kdw.newsapp.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.browse.MediaBrowser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

        val touchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = savedArticleAdapter.differ.currentList[position]
                articleViewModel.deletedNews(article)

                val builder = AlertDialog.Builder(activity)
                    .setTitle("뉴스 삭제")
                    .setMessage("뉴스를 삭제하시겠습니까?")
                    .setPositiveButton("예",
                        { dialog, which ->
                            articleViewModel.deletedNews(article)
                        })
                    .setNegativeButton("아니요",
                        {   dialog, which ->
                        articleViewModel.savedNews(article)
                        })

                builder.show()

                //Snackbar.make(savingBinding.root, "뉴스 삭제 완료", Snackbar.LENGTH_LONG)
                  //  .show()
            }
        }

        ItemTouchHelper(touchHelper).attachToRecyclerView(savingBinding.savedArticleRecyclerView)

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