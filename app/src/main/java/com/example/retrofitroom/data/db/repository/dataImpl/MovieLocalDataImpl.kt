package com.example.retrofitroom.data.db.repository.dataImpl

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.db.repository.dataSource.MovieLocalDataSource
import com.example.retrofitroom.data.db.cache.MovieInterface
import com.example.retrofitroom.domain.model.Result

class MovieLocalDataImpl(private val movieDao: MovieInterface) : MovieLocalDataSource {

    override fun getSavedMovie(): LiveData<List<Result>> {
        return movieDao.getAllMovie()
    }

    override suspend fun insert(movie: Result) {
        return movieDao.insert(movie)
    }

    override suspend fun deleteMovie(movie: Result) {
        return movieDao.deleteMovie(movie)
    }
}