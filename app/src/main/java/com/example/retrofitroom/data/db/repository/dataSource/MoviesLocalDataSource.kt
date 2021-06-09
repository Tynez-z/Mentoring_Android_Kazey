package com.example.retrofitroom.data.db.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.entity.Result

interface MoviesLocalDataSource {
    fun getSavedMovies(): LiveData<List<Result>>
    suspend fun insert(movies: Result)
    suspend fun deleteMovies(movies: Result)
}