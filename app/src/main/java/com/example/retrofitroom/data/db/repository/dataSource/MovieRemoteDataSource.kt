package com.example.retrofitroom.data.db.repository.dataSource

import com.example.retrofitroom.domain.model.MoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovie(): Response<MoviesResponse>
}