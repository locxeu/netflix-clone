package com.loc.newsapp.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.MovieLocal
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String,sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertMovie(movieLocal: MovieLocal)

    suspend fun deleteMovie(movieLocal: MovieLocal)

    fun selectMovies(): Flow<List<MovieLocal>>

    suspend fun selectMovie(id: String): MovieLocal?
}