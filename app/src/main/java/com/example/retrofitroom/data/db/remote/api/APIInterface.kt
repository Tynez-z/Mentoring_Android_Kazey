package com.example.retrofitroom.data.db.remote.api

import com.example.retrofitroom.common.API_KEY
import com.example.retrofitroom.domain.entity.MoviesResponse
import retrofit2.Response

interface APIInterface {
    suspend fun getMovies(apiKey: String = API_KEY): Response<MoviesResponse>
}