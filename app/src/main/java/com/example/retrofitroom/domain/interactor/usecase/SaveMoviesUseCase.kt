package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.domain.repository.MoviesRepository
import com.example.retrofitroom.domain.entity.Result

class SaveMoviesUseCase (private val moviesRepository: MoviesRepository) {
    suspend fun execute (result : Result) = moviesRepository.insert(result)
}