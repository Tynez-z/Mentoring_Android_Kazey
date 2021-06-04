package com.example.retrofitroom.data.repository.dataImpl

import com.example.retrofitroom.data.remote.api.MovieAPI
import com.example.retrofitroom.data.repository.dataSource.MovieRemoteDataSource
import com.example.retrofitroom.model.MoviesResponse
import retrofit2.Response

class MovieRemoteDataImpl (private val movieAPI: MovieAPI) : MovieRemoteDataSource {

    override suspend fun getMovie(): Response<MoviesResponse> {
        return movieAPI.getMovie()
    }
}