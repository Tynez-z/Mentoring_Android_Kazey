package com.example.retrofitroom.domain.entity

data class MoviesResponse(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
)