package com.loc.newsapp.presentation.bookmark

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.profile.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {


    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getListMovieLiked()
        Log.d("BookmarkViewModel", "init:BookmarkViewModel")
    }

    private fun getListMovieLiked() {
        profileUseCases.getListFavouriteMovieUseCases().onEach {
            _state.value = state.value.copy(moviesLiked = it)
        }.launchIn(viewModelScope)
    }
}