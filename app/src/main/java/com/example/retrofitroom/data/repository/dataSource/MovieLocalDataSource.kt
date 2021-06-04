package com.example.retrofitroom.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.retrofitroom.model.Result

interface MovieLocalDataSource {

    fun getSavedMovie(): LiveData<List<Result>>
    suspend fun insert(movie: Result)
    suspend fun deleteMovie(movie: Result)

}