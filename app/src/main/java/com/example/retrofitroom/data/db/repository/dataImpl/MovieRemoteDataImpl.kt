package com.example.retrofitroom.data.db.repository.dataImpl

import com.example.retrofitroom.data.db.remote.api.APIInterface
import com.example.retrofitroom.data.db.repository.dataSource.MovieRemoteDataSource
import com.example.retrofitroom.model.MoviesResponse
import retrofit2.Response

class MovieRemoteDataImpl (private val movieAPI: APIInterface) : MovieRemoteDataSource {

    override suspend fun getMovie(): Response<MoviesResponse> {
        return movieAPI.getMovie()
    }
}