package com.example.retrofitroom.data.db.repository.dataImpl

import com.example.retrofitroom.data.db.remote.api.APIInterface
import com.example.retrofitroom.data.db.repository.dataSource.MoviesRemoteDataSource
import com.example.retrofitroom.domain.entity.MoviesResponse
import retrofit2.Response

class MoviesRemoteDataImpl (private val moviesAPI: APIInterface) : MoviesRemoteDataSource {
    override suspend fun getMovies(): Response<MoviesResponse> {
        return moviesAPI.getMovies()
    }
}