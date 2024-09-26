package com.loc.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.model.Article

class SearchNewsPagingSource(
    private val movieApi: MovieApi,
    private val searchQuery: String,
    private val page: Int
) : PagingSource<Int, MovieInfo>() {

    override fun getRefreshKey(state: PagingState<Int, MovieInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalMoviesCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieInfo> {
        val page = params.key ?: 1
        return try {
            // Call max 8 page when user dont search anything
            if (searchQuery.isEmpty() && page > 8) {
                return LoadResult.Page(
                    data = emptyList(),
                    nextKey = null,
                    prevKey = null
                )
            }
            val movieResponse =
                movieApi.searchMovie(keyword = searchQuery, page = page)
            totalMoviesCount += movieResponse.data.items.size
            val articles = movieResponse.data.items.distinctBy { it._id } // Remove Duplicates
            LoadResult.Page(
                data = articles,
                nextKey = if (totalMoviesCount == movieResponse.data.params.pagination.totalItems) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}