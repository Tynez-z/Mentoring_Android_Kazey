package com.example.retrofitroom.data.db.repository.dataSource

import com.example.retrofitroom.domain.entity.MoviesResponse
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getMovies(): Response<MoviesResponse>
}