package com.example.retrofitroom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitroom.domain.interactor.usecase.*

//class MovieViewModelProviderFactory(val moviesRepository: MovieRepositoryImpl) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return MovieViewModel(moviesRepository) as T
//    }
//}

class MovieViewModelProviderFactory(
    private val deleateSavedMovieUseCase: DeleateSavedMovieUseCase,
    private val getMovieUseCase: GetMovieUseCase,
    private val getSavedNewsUseCase: GetSavedMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(getMovieUseCase, deleateSavedMovieUseCase, getSavedNewsUseCase, saveMovieUseCase) as T
    }
}

