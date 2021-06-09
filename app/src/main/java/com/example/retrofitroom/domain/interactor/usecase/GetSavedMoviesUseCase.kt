package com.example.retrofitroom.domain.interactor.usecase

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.repository.MoviesRepository
import com.example.retrofitroom.domain.entity.Result

class GetSavedMoviesUseCase(private val moviesRepository: MoviesRepository) {
    fun execute(): LiveData<List<Result>> = moviesRepository.getSavedMovies()
}