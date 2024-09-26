package com.loc.newsapp.domain.usecases.movie

import androidx.paging.PagingData
import com.loc.newsapp.data.remote.dto.MovieDetailDto
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetail (
    private val homeRepository: HomeRepository
){
    suspend operator fun invoke(movieId: String): Flow<MovieDetailDto> {
        return homeRepository.getMovieDetails(movieId)
    }
}