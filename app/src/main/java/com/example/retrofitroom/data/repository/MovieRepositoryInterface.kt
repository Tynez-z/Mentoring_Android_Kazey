package com.example.retrofitroom.data.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.model.MoviesResponse
import com.example.retrofitroom.model.Result
import retrofit2.Response

interface MovieRepositoryInterface {
    suspend fun getMovie(): Response<MoviesResponse>
    suspend fun upsert(movie : Result) : Long
    suspend fun deleteMovie(movie: Result)
    fun getSavedMovie() : LiveData<List<Result>>
}