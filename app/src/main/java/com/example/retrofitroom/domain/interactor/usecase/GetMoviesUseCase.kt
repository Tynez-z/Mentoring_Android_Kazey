package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.data.db.repository.MoviesRepository
import com.example.retrofitroom.domain.entity.MoviesResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository) {
    suspend fun execute(): Response<MoviesResponse> {
        return moviesRepository.getMovies()
    }
}
