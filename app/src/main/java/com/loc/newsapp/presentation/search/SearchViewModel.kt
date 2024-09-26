package com.loc.newsapp.presentation.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.log
import com.loc.newsapp.domain.usecases.movie.MoviesUseCase
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.news.SearchNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state
    init {
        searchNews()
    }
    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(keyword = event.keyword)
            }

            is SearchEvent.SearchMovie -> {
                _state.update { it.copy(loading = false) }
                searchNews()
            }
        }
    }

    private fun searchNews() {
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            try {
                val movie = moviesUseCase.searchMovie(
                    keyword = state.value.keyword,
                    page = 1
                ).cachedIn(viewModelScope)
                _state.value = state.value.copy(movie = movie)
                _state.update { it.copy(loading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(loading = false) }
                // Handle error if needed
            }
        }
    }

}