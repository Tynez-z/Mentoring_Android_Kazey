package com.example.retrofitroom.domain.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.model.MoviesResponse
import com.example.retrofitroom.model.Result
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovie(): Response<MoviesResponse>
    suspend fun insert (movie : Result)
    suspend fun deleteMovie(movie: Result)
    fun getSavedMovie() : LiveData<List<Result>>
}