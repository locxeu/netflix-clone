package com.loc.newsapp.domain.usecases.profile

import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.MovieLocal
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.repository.ProfileRepository


class UpsertFavouriteMovieUseCases(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(movieLocal: MovieLocal){
        profileRepository.upsertMovie(movieLocal)
    }

}