package com.loc.newsapp.domain.usecases.profile

import com.loc.newsapp.domain.model.MovieLocal
import com.loc.newsapp.domain.repository.ProfileRepository

class GetDetailFavouriteMovieUseCases (
    private val productRepository: ProfileRepository

){
    suspend operator fun invoke(movieId: String): MovieLocal?{
        return productRepository.selectMovie(movieId)
    }
}