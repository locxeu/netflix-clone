package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.remote.HomeMoviePagingSource
import com.loc.newsapp.data.remote.MovieApi
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.remote.NewsPagingSource
import com.loc.newsapp.data.remote.SearchNewsPagingSource
import com.loc.newsapp.data.remote.dto.MovieDetailDto
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val movieApi: MovieApi,
    ):HomeRepository {
    override fun getHome(): Flow<PagingData<MovieInfo>> {
        return Pager(
            config = PagingConfig(pageSize = 24),
            pagingSourceFactory = {
                HomeMoviePagingSource(
                    movieApi = movieApi,
                )
            }
        ).flow
    }

    override suspend fun getMovieDetails(movieId: String): Flow<MovieDetailDto> {
        return flow { emit(movieApi.getMovieDetail(movieId)) }
    }

    override fun searchMovie(keyword: String, page: Int): Flow<PagingData<MovieInfo>> {
        return Pager(
            config = PagingConfig(pageSize = 24),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = keyword,
                    movieApi = movieApi,
                    page = page
                )
            }
        ).flow
    }

}