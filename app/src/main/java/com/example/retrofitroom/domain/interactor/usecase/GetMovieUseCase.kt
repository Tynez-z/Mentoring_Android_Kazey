package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.Resource
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.model.MoviesResponse
import retrofit2.Response

class GetMovieUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(): Response<MoviesResponse> {
        return movieRepository.getMovie()
    }

}
