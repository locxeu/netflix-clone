package com.loc.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.model.Article

class HomeMoviePagingSource(
    private val movieApi: MovieApi,
): PagingSource<Int, MovieInfo>() {
    private var totalMovieCount = 0

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, MovieInfo> {
        val page = params.key ?: 1
        return try {
            val homeResponse = movieApi.getHome()
            Log.d("homeResponse", "load: $homeResponse")
            totalMovieCount += homeResponse.data.items.size
            val articles = homeResponse.data.items.distinctBy { it.time } // Remove Duplicates
            PagingSource.LoadResult.Page(
                data = articles,
                nextKey =null,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            PagingSource.LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}