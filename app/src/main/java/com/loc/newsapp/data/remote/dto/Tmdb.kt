package com.loc.newsapp.data.remote.dto

data class Tmdb(
    val id: String,
    val season: Int,
    val type: String,
    val vote_average: Double,
    val vote_count: Int
)