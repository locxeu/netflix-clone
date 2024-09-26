package com.loc.newsapp.data.remote.dto

data class HomeMovieResponse(
    val data: Data,
    val message: String,
    val status: String
)

data class Data(
    val APP_DOMAIN_CDN_IMAGE: String,
    val APP_DOMAIN_FRONTEND: String,
    val items: List<MovieInfo>,
    val itemsSportsVideos: List<Any>,
    val params: Params,
    val seoOnPage: SeoOnPage,
    val type_list: String
)

data class MovieInfo(
    val _id: String,
    val category: List<Category>,
    val country: List<Country>,
    val episode_current: String,
    val lang: String,
    val modified: Modified,
    val name: String,
    val origin_name: String,
    val quality: String,
    val slug: String,
    val sub_docquyen: Boolean,
    val thumb_url: String,
    val time: String,
    val type: String,
    val year: Int
)

data class Params(
    val filterCategory: List<Any>,
    val filterCountry: List<Any>,
    val filterYear: String,
    val itemsSportsVideosUpdateInDay: Int,
    val itemsUpdateInDay: Int,
    val pagination: Pagination,
    val sortField: String,
    val totalSportsVideos: Int,
    val type_slug: String
)

data class SeoOnPage(
    val descriptionHead: String,
    val og_image: List<String>,
    val og_type: String,
    val titleHead: String
)

data class Pagination(
    val currentPage: Int,
    val pageRanges: Int,
    val totalItems: Int,
    val totalItemsPerPage: Int
)