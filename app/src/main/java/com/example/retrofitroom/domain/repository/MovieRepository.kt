package com.example.retrofitroom.domain.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.model.MoviesResponse
import com.example.retrofitroom.domain.model.Result
import retrofit2.Response

interface MovieRepository {
    //TODO rename funcs
    suspend fun getMovie(): Response<MoviesResponse>
    suspend fun insert (movie : Result)
    suspend fun deleteMovie(movie: Result)
    fun getSavedMovie() : LiveData<List<Result>>
}