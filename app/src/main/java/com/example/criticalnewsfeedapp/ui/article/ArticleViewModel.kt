package com.example.criticalnewsfeedapp.ui.article

import androidx.lifecycle.ViewModel
import com.example.criticalnewsfeedapp.domain.Article
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ArticleViewModel : ViewModel() {
    var article: Article? = null

    fun getFormattedPublishDate() : String {
        return  OffsetDateTime.parse(article?.publishedAt).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }
}