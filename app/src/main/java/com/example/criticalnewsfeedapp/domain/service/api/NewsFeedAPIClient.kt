package com.example.criticalnewsfeedapp.domain.service.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsFeedAPIClient {
    private const val TAG = "NewsFeedAPIClient"
    private const val BASE_URL = "https://newsapi.org/v2/"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            Interceptor { chain ->
                val request = chain.request()
                Log.i(TAG, "Configuring api key for news feed")
                val newRequest = request.newBuilder()
                    .header("X-Api-Key", "3c9072283d27404d9dbd21355afd93a5")
                    .build()
                Log.i(TAG, "calling $request.url")
                chain.proceed(newRequest)
                    .also { response ->
                        Log.i(TAG, "Response body: ${response.peekBody(2048).string()}")
                    }
            }
        )
        .build()

    private val newsFeedAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClient() : Retrofit = newsFeedAPI
}