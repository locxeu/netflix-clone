package com.loc.newsapp.domain.usecases.profile

import com.loc.newsapp.domain.model.MovieLocal
import com.loc.newsapp.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetListFavouriteMovieUseCases(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(): Flow<List<MovieLocal>> {
        return profileRepository.selectMovies()
    }
}

