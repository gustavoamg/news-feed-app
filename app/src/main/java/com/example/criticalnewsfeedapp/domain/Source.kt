package com.example.criticalnewsfeedapp.domain

import java.io.Serializable

data class Source(
    val id: String,
    val name: String) : Serializable {

    var description: String? = null
    var url: String? = null
    var category: String? = null
    var language: String? = null
    var country: String? = null
}