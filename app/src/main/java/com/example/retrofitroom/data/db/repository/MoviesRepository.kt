package com.example.retrofitroom.data.db.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.entity.MoviesResponse
import com.example.retrofitroom.domain.entity.Result
import retrofit2.Response

interface MoviesRepository {
    fun getSavedMovies() : LiveData<List<Result>>
    suspend fun getMovies(): Response<MoviesResponse>
    suspend fun insert (movies : Result) //TODO rename insert what?
    suspend fun deleteMovies(movies: Result)
}