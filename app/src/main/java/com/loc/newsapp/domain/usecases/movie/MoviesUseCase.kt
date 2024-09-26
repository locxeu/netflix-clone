package com.loc.newsapp.domain.usecases.movie

data class MoviesUseCase(
    val getHome: GetHome,
    val getMovieDetails: GetMovieDetail,
    val searchMovie: SearchMovie
)