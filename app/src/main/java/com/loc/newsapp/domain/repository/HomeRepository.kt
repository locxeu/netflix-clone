package com.loc.newsapp.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.data.remote.dto.MovieDetailDto
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getHome(): Flow<PagingData<MovieInfo>>
    suspend fun getMovieDetails(movieId: String):Flow<MovieDetailDto>
    fun searchMovie(keyword: String,page: Int): Flow<PagingData<MovieInfo>>

}