package com.example.retrofitroom.data.db.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.entity.MoviesResponse
import com.example.retrofitroom.domain.entity.Result
import retrofit2.Response

interface MoviesRepository {
    suspend fun getMovies(): Response<MoviesResponse>
    suspend fun insert (movies : Result)
    suspend fun deleteMovies(movies: Result)
    fun getSavedMovies() : LiveData<List<Result>>
}