package com.example.retrofitroom.domain.interactor.usecase

import androidx.lifecycle.LiveData
import com.example.retrofitroom.domain.repository.MoviesRepository
import com.example.retrofitroom.domain.entity.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSavedMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository) {
    fun execute(): LiveData<List<Result>> = moviesRepository.getSavedMovies()
}