package com.example.retrofitroom.data.db.remote.api

import com.example.retrofitroom.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI : APIInterface {
    @GET("/3/movie/popular")
    override suspend fun getMovie(@Query("api_key") api_key: String): Response<MoviesResponse>
}
