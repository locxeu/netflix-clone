package com.loc.newsapp.domain.usecases.profile

data class ProfileUseCases (
    val getListFavouriteMovieUseCases: GetListFavouriteMovieUseCases,
    val upsertFavouriteMovieUseCases: UpsertFavouriteMovieUseCases,
    val unFavouriteMovieUseCases: UnFavouriteMovieUseCases,
    val getMovieDetailUseCases: GetDetailFavouriteMovieUseCases
)
