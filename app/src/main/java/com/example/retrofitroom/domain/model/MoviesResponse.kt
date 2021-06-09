package com.example.retrofitroom.domain.model

//TODO rename fields from @total_pages to CamelStyle - totalPages
data class MoviesResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)