package com.example.retrofitroom.data.db.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.db.repository.dataSource.MovieLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MovieRemoteDataSource
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.domain.model.MoviesResponse
import com.example.retrofitroom.domain.model.Result
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    //TODO rename methods
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