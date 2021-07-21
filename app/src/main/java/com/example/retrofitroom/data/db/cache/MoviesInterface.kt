package com.example.retrofitroom.data.db.cache

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.entity.Result

interface MoviesInterface {
    fun getAllMovies(): LiveData<List<Result>>
    suspend fun insert(movies: Result) //TODO rename insert what?
    suspend fun deleteMovies(movies: Result)
}