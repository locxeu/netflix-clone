package com.loc.newsapp.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val keyword: String): SearchEvent()

    object SearchMovie : SearchEvent()
}