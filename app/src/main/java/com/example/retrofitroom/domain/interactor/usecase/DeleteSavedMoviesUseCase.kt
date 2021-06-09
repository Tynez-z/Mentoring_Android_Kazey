package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.domain.entity.Result
import com.example.retrofitroom.domain.repository.MoviesRepository

class DeleteSavedMoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(result : Result) = moviesRepository.deleteMovies(result)
}