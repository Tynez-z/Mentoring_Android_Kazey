package com.example.retrofitroom.data.db.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.db.repository.dataSource.MoviesLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MoviesRemoteDataSource
import com.example.retrofitroom.domain.repository.MoviesRepository
import com.example.retrofitroom.domain.entity.MoviesResponse
import com.example.retrofitroom.domain.entity.Result
import retrofit2.Response

class MoviesRepositoryImpl(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    //TODO rename methods
    override suspend fun getMovies(): Response<MoviesResponse> {
        return moviesRemoteDataSource.getMovies()
    }

    override suspend fun insert(movies: Result) {
        moviesLocalDataSource.insert(movies)
    }

    override suspend fun deleteMovies(movies: Result) {
        moviesLocalDataSource.deleteMovies(movies)
    }

    override fun getSavedMovies(): LiveData<List<Result>> {
        return moviesLocalDataSource.getSavedMovies()
    }
}