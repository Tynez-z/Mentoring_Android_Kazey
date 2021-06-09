package com.example.retrofitroom.data.db.remote.api

import com.example.retrofitroom.domain.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI : APIInterface {

    companion object{
        const val GET_MOVIE = "/3/movie/popular"
        const val API_KEY = "api_key"
    }

    @GET(GET_MOVIE)
    override suspend fun getMovie(@Query(API_KEY) apiKey: String): Response<MoviesResponse>
}
