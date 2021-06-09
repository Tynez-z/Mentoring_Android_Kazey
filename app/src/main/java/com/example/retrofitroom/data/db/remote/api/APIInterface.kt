package com.example.retrofitroom.data.db.remote.api

import com.example.retrofitroom.common.API_KEY
import com.example.retrofitroom.domain.model.MoviesResponse
import retrofit2.Response

interface APIInterface {
    suspend fun getMovie(apiKey: String = API_KEY): Response<MoviesResponse>
}