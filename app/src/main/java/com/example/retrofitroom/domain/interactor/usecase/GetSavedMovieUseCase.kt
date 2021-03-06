package com.example.retrofitroom.domain.interactor.usecase

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.model.Result

class GetSavedMovieUseCase(private val movieRepository: MovieRepository) {
    fun execute(): LiveData<List<Result>> = movieRepository.getSavedMovie()
}