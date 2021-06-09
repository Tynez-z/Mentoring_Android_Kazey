package com.example.retrofitroom.domain.interactor.usecase

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.domain.model.Result

//TODO rename use case
class GetSavedMovieUseCase(private val movieRepository: MovieRepository) {
    fun execute(): LiveData<List<Result>> = movieRepository.getSavedMovie()
}