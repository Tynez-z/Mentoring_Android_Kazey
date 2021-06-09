package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.domain.repository.MoviesRepository
import com.example.retrofitroom.domain.entity.MoviesResponse
import retrofit2.Response

class GetMoviesUseCase (private val moviesRepository: MoviesRepository) {
    suspend fun execute(): Response<MoviesResponse> {
        return moviesRepository.getMovies()
    }
}
