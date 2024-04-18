package com.example.criticalnewsfeedapp.domain.service.api

import com.example.criticalnewsfeedapp.domain.Article

data class NewsFeedResult(val status: String?)  {
    var totalResults: Int? = null
    var articles: List<Article>? = null
}