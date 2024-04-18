package com.example.criticalnewsfeedapp.domain.service

import javax.inject.Inject
import javax.inject.Singleton

interface IAuthenticationService

@Singleton
class AuthenticationService @Inject constructor() : IAuthenticationService {
    var userAuthenticated = false
}