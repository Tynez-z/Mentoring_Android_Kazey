package com.example.retrofitroom.data.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.repository.dataSource.MovieLocalDataSource
import com.example.retrofitroom.data.repository.dataSource.MovieRemoteDataSource
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.model.MoviesResponse
import com.example.retrofitroom.model.Result
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getMovie(): Response<MoviesResponse> {
        return movieRemoteDataSource.getMovie()
    }

    override suspend fun insert(movie: Result) {
        movieLocalDataSource.insert(movie)
    }

    override suspend fun deleteMovie(movie: Result) {
        movieLocalDataSource.deleteMovie(movie)
    }

    override fun getSavedMovie(): LiveData<List<Result>> {
        return movieLocalDataSource.getSavedMovie()
    }

}