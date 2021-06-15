package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.domain.entity.Result
import com.example.retrofitroom.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteSavedMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository) {
    suspend fun execute(result : Result) = moviesRepository.deleteMovies(result)
}