package com.example.pagingdemo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingdemo.model.UserList

import com.example.pagingdemo.network.UserAPI
import java.lang.Exception

class UserPagingSource(private val quoteAPI: UserAPI) : PagingSource<Int, UserList.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserList.Data> {
        return try {
            val position = params.key ?: 1
            val response = quoteAPI.getUser(position)

            return LoadResult.Page(
                data = response.data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.total_pages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserList.Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}