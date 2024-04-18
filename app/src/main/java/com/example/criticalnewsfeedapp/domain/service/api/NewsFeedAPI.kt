package com.example.criticalnewsfeedapp.domain.service.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsFeedAPI {
    @GET("top-headlines/")
    fun getTopHeadlinesFromMainSource(
        @Query("sources") source: String,
        @Query("pageSize") pageSize: String,
        @Query("page") page: String
    ): Call<NewsFeedResult>
}