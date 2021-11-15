package com.kdw.newsapp.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kdw.newsapp.R
import com.kdw.newsapp.databinding.ItemArticleBinding
import com.kdw.newsapp.model.Article
import com.kdw.newsapp.ui.fragment.ArticleFragmentDirections

class ArticleAdapter(val context: Context): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean{
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)
    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)

        return ArticleViewHolder(ItemArticleBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            holder.binding.apply {
                article?.let {
                    Glide.with(context).load(it.urlToImage).into(articleImage)
                    articleTitle.text = it.title
                    articleDescription.text = it.description
                    articlePublished.text = it.publishedAt
                    articleSource.text = it.source?.name
                }

                setOnItemClickListener {
                    onItemClickListener?.let {
                        it(article)
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}