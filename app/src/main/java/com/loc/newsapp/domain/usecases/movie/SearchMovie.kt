package com.loc.newsapp.domain.usecases.movie

import androidx.paging.PagingData
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class SearchMovie(
    private val searchRepository: HomeRepository
) {
    operator fun invoke(keyword: String,page: Int): Flow<PagingData<MovieInfo>> {
        return searchRepository.searchMovie (keyword = keyword,page = page)
    }
}