package com.example.retrofitroom.data.db.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.db.repository.dataSource.MoviesLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MoviesRemoteDataSource
import com.example.retrofitroom.domain.entity.MoviesResponse
import com.example.retrofitroom.domain.entity.Result
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun getMovies(): Response<MoviesResponse> {
        return moviesRemoteDataSource.getMovies()
    }

    override suspend fun insert(movies: Result) {  //TODO rename insert what?
        moviesLocalDataSource.insert(movies)
    }

    override suspend fun deleteMovies(movies: Result) {
        moviesLocalDataSource.deleteMovies(movies)
    }

    override fun getSavedMovies(): LiveData<List<Result>> {
        return moviesLocalDataSource.getSavedMovies()
    }
}