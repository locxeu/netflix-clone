package com.loc.newsapp.data.remote

import com.loc.newsapp.data.remote.dto.HomeMovieResponse
import com.loc.newsapp.data.remote.dto.MovieDetailDto
import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/v1/api/home")
    suspend fun getHome(
    ): HomeMovieResponse

    @GET("/phim/id/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String
    ): MovieDetailDto

    @GET("/v1/api/tim-kiem")
    suspend fun searchMovie(
        @Query("keyword") keyword: String,
        @Query("page") page: Int
    ): HomeMovieResponse

}