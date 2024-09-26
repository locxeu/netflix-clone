package com.loc.newsapp.data.repository

import androidx.paging.PagingData
import com.loc.newsapp.data.local.MoviesDao
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.MovieLocal
import com.loc.newsapp.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val moviesDao: MoviesDao
):ProfileRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        TODO("Not yet implemented")
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertMovie(movieLocal: MovieLocal) {
        moviesDao.upsert(movieLocal)
    }

    override suspend fun deleteMovie(movieLocal: MovieLocal) {
       moviesDao.delete(movieLocal)
    }

    override fun selectMovies(): Flow<List<MovieLocal>> {
       return moviesDao.getMovies()
    }

    override suspend fun selectMovie(id: String): MovieLocal? {
        return moviesDao.getMovie(id)
    }
}