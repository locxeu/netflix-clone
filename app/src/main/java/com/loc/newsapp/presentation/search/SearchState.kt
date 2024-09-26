package com.loc.newsapp.presentation.search

import androidx.paging.PagingData
import com.loc.newsapp.data.remote.dto.MovieInfo
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val keyword: String = "",
    val movie: Flow<PagingData<MovieInfo>>? = null,
    val loading: Boolean = false
)

