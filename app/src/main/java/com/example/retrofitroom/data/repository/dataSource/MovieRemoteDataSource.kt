package com.example.retrofitroom.data.repository.dataSource

import com.example.retrofitroom.model.MoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovie(): Response<MoviesResponse>
}