package com.example.pagingdemo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pagingdemo.network.UserAPI
import com.example.pagingdemo.paging.UserPagingSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    fun getUsera() = Pager(
        config = PagingConfig(pageSize = 6, maxSize = 100),
        pagingSourceFactory = { UserPagingSource(userAPI) }
    ).liveData
}