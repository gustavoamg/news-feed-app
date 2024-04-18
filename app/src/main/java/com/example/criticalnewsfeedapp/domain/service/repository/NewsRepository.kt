package com.example.criticalnewsfeedapp.domain.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.criticalnewsfeedapp.domain.Article
import com.example.criticalnewsfeedapp.domain.service.api.NewsFeedAPI
import com.example.criticalnewsfeedapp.domain.service.api.NewsFeedAPIClient
import com.example.criticalnewsfeedapp.domain.service.api.NewsFeedResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val source : String) {

    private val lTAG = "NewsRepository"

    private val retrofitClient = NewsFeedAPIClient.getClient()
    private val api = retrofitClient.create(NewsFeedAPI::class.java)

    private val pageSize = 25
    private var pageNumber = 1

    private val articleList: ArrayList<Article> = emptyList<Article>().toCollection(ArrayList())

    fun getTopHeadLinesFromMainSource() : LiveData<List<Article>> {
        val result : MutableLiveData<List<Article>> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            api.getTopHeadlinesFromMainSource(source, pageSize.toString(), pageNumber.toString()).enqueue(object : Callback<NewsFeedResult> {
                override fun onResponse(call: Call<NewsFeedResult>, response: Response<NewsFeedResult>) {
                    Log.i("NewsRepository", "Response body: ${response.body()}")
                    if(response.isSuccessful) {
                        val articles = response.body()!!.articles!!
                        if(!articles.isEmpty()) {
                            articleList.addAll(articles)
                            pageNumber++
                            result.postValue(articleList)
                        }
                    }
                }

                override fun onFailure(call: Call<NewsFeedResult>, exception: Throwable) {
                    Log.e(lTAG, "Could not get results for ${call.request().url} with result ${exception.message}", exception)
                    result.postValue(ArrayList())
                }
            }
            )
        }
        return result
    }
}