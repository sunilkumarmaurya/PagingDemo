package com.example.pagingdemo.network

import com.example.pagingdemo.model.User
import com.example.pagingdemo.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateUser {
    @POST("users")
    fun createUser(@Body param: User): Call<UserResponse>
}