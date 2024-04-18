package com.example.criticalnewsfeedapp.di

import com.example.criticalnewsfeedapp.domain.service.AuthenticationService
import com.example.criticalnewsfeedapp.domain.service.IAuthenticationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {

    @Binds
    abstract fun getAuthenticationService(authenticationService: AuthenticationService): IAuthenticationService
}