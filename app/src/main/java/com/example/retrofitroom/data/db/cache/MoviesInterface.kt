package com.example.retrofitroom.data.db.cache

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.entity.Result

interface MoviesInterface {
    suspend fun insert(movies: Result)
    fun getAllMovies(): LiveData<List<Result>>
    suspend fun deleteMovies(movies: Result)
}