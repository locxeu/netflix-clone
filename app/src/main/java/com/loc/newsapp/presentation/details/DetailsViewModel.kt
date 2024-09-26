package com.loc.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.data.remote.dto.MovieDetailDto
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.usecases.movie.MoviesUseCase
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.profile.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val profileUseCases: ProfileUseCases

) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailDto?>(null)
    val movieDetails: StateFlow<MovieDetailDto?> = _movieDetails

    var sideEffect by mutableStateOf<String?>(null)
        private set

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.FetchMovieDetails -> {
                fetchMovieDetails(event.movieId)
            }
            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }

            is DetailsEvent.AddRemoveFavorite -> {
                viewModelScope.launch {
                    val movieLocal = profileUseCases.getMovieDetailUseCases(movieId = event.movieLocal.id)
                    if (movieLocal == null){
                        profileUseCases.upsertFavouriteMovieUseCases(movieLocal = event.movieLocal)
                    }else{
                        profileUseCases.unFavouriteMovieUseCases(movieLocal = event.movieLocal)
                    }
                    checkIfFavorite(event.movieLocal.id)

                }
            }
        }
    }

    private fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            try {
                _movieDetails.value = moviesUseCase.getMovieDetails(movieId).first()
                checkIfFavorite(movieId)
            } catch (e: Exception) {
                sideEffect = "Failed to fetch movie details"
            }
        }
    }

    private fun checkIfFavorite(movieId: String) {
        viewModelScope.launch {
            _isFavorite.value = profileUseCases.getMovieDetailUseCases(movieId) != null
        }
    }

}