package com.loc.newsapp.data.remote.dto

data class MovieDetailDto(
    val episodes: List<Episode>,
    val movie: Movie,
    val msg: String,
    val status: Boolean
)