package com.loc.newsapp.data.remote.dto

data class searchMovieDTO(
    val `data`: Data,
    val message: String,
    val status: String
)

data class BreadCrumb(
    val isCurrent: Boolean,
    val name: String,
    val position: Int
)

data class Item(
    val _id: String,
    val category: List<Category>,
    val chieurap: Boolean,
    val country: List<Country>,
    val episode_current: String,
    val lang: String,
    val modified: Modified,
    val name: String,
    val origin_name: String,
    val poster_url: String,
    val quality: String,
    val slug: String,
    val sub_docquyen: Boolean,
    val thumb_url: String,
    val time: String,
    val type: String,
    val year: Int
)

