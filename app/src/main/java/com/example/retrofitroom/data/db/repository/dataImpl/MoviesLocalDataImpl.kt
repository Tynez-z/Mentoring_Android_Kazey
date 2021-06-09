package com.example.retrofitroom.data.db.repository.dataImpl

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.db.cache.MoviesInterface
import com.example.retrofitroom.data.db.repository.dataSource.MoviesLocalDataSource
import com.example.retrofitroom.domain.entity.Result

class MoviesLocalDataImpl(private val movieDao: MoviesInterface) : MoviesLocalDataSource {
    override fun getSavedMovies(): LiveData<List<Result>> {
        return movieDao.getAllMovie()
    }

    override suspend fun insert(movies: Result) {
        return movieDao.insert(movies)
    }

    override suspend fun deleteMovies(movies: Result) {
        return movieDao.deleteMovie(movies)
    }
}