package com.example.retrofitroom.data.repository.dataImpl

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.repository.dataSource.MovieLocalDataSource
import com.example.retrofitroom.data.local.db.MovieDao
import com.example.retrofitroom.model.Result

class MovieLocalDataImpl(private val movieDao: MovieDao) : MovieLocalDataSource {

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