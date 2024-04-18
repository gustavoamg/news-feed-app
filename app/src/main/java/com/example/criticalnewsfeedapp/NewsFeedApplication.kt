package com.example.criticalnewsfeedapp

import android.app.Application
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class NewsFeedApplication : Application() {
    companion object {
        private var isPicassoInitialized = false
    }

    override fun onCreate() {
        super.onCreate()
        if(!isPicassoInitialized) {
            Picasso.setSingletonInstance(Picasso.Builder(this).build())
            isPicassoInitialized = true
        }
    }
}