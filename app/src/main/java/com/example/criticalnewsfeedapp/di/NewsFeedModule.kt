package com.example.criticalnewsfeedapp.di

import android.content.Context
import com.example.criticalnewsfeedapp.R
import com.example.criticalnewsfeedapp.domain.service.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class NewsFeedModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(@ApplicationContext context: Context ): NewsRepository =
        NewsRepository(context.resources.getString(R.string.newsSource))
}