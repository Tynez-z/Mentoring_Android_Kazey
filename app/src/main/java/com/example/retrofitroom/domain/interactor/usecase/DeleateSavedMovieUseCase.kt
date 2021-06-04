package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.model.Result

class DeleateSavedMovieUseCase(private val movieRepository: MovieRepository) {
suspend fun execute(result : Result) = movieRepository.deleteMovie(result)
}