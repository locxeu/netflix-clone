package com.loc.newsapp.presentation.bookmark

import com.loc.newsapp.domain.model.MovieLocal

data class BookmarkState(
    val moviesLiked: List<MovieLocal> = emptyList()
)
