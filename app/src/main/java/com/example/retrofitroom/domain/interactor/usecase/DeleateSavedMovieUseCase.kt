package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.domain.model.Result
import com.example.retrofitroom.domain.repository.MovieRepository

//TODO rename Use Case
class DeleateSavedMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(result : Result) = movieRepository.deleteMovie(result)
}