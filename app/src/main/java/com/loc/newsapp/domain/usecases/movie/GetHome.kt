package com.loc.newsapp.domain.usecases.movie

import androidx.paging.PagingData
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.HomeRepository
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetHome(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<PagingData<MovieInfo>> {
        return homeRepository.getHome()
    }
}