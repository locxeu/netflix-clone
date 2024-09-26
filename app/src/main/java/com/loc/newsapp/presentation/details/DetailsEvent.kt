package com.loc.newsapp.presentation.details

import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.MovieLocal

sealed class DetailsEvent {

    data class FetchMovieDetails(val movieId: String) : DetailsEvent()
    data class AddRemoveFavorite(val movieLocal: MovieLocal) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}