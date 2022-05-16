package com.example.pagingdemo.model

data class UserResponse(
    val createdAt: String,
    val id: String,
    val job: String,
    val name: String
)
data class User(val id: String?, val name: String?, val job: String?)