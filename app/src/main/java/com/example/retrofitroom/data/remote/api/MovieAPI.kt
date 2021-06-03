package com.example.retrofitroom.data.remote.api

import com.example.retrofitroom.Constants.Companion.API_KEY
import com.example.retrofitroom.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/3/movie/popular")
    suspend fun getMovie(
        @Query("api_key")
        api_key: String = API_KEY) : Response<MoviesResponse>
}