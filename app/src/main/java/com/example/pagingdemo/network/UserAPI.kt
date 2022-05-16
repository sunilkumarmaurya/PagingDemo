package com.example.pagingdemo.network


import com.example.pagingdemo.model.User
import com.example.pagingdemo.model.UserList
import com.example.pagingdemo.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserAPI {
    @GET("users")
    suspend fun getUser(@Query("page") page: Int): UserList
}