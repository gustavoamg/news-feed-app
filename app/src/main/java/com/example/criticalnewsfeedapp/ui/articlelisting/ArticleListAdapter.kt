package com.example.criticalnewsfeedapp.ui.articlelisting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.criticalnewsfeedapp.R
import com.example.criticalnewsfeedapp.domain.Article
import com.example.criticalnewsfeedapp.ui.article.ArticleFragment
import com.squareup.picasso.Picasso


class ArticleListAdapter(var data: List<Article>?, private val loadMoreDataCallback: () -> Unit): RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitleTv : TextView
        val articleImageIv: ImageView

        init {
            articleTitleTv = view.findViewById(R.id.article_title_tv)
            articleImageIv = view.findViewById(R.id.article_picture_iv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == data?.size!! - 2) {
            loadMoreDataCallback()
        }

        holder.articleTitleTv.text = data!![position].title
        Picasso.get().load(data!![position].urlToImage)
            .into(holder.articleImageIv)
        holder.itemView.setOnClickListener {view ->
            val bundle = bundleOf(ArticleFragment.ARGUMENT_ARTICLE to data!![position])
            view.findNavController().navigate(R.id.articleFragment, bundle)
        }
    }
}