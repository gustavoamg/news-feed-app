package com.example.criticalnewsfeedapp.ui.articlelisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.criticalnewsfeedapp.domain.Article
import com.example.criticalnewsfeedapp.domain.service.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class ArticlesListingViewModel @Inject constructor ( private val newsRepository : NewsRepository) : ViewModel() {
    val newsLiveData: LiveData<List<Article>>
    val articleListAdapter: ArticleListAdapter
    val newsLiveDataObserver: Observer<List<Article>>

    init {
        newsLiveData = newsRepository.getTopHeadLinesFromMainSource()
        articleListAdapter = ArticleListAdapter(newsLiveData.value, ::loadMoreData )

        newsLiveDataObserver = Observer { articleList ->
            articleListAdapter.data = articleList.sortedByDescending { OffsetDateTime.parse(it.publishedAt) }
            articleListAdapter.notifyDataSetChanged()
        }
    }

    private fun loadMoreData() {
        newsRepository.getTopHeadLinesFromMainSource()
    }
}