package com.example.retrofitroom.domain.interactor.usecase

import com.example.retrofitroom.data.repository.MovieRepositoryImpl
import com.example.retrofitroom.model.MoviesResponse
import retrofit2.Response

open class GetMovieUseCase(private val movieRepository: MovieRepositoryImpl) {
     suspend fun getMovieFromUseCase () : Response<MoviesResponse> = movieRepository.getMovie()
     }
