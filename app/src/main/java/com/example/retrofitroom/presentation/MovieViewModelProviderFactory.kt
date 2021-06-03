package com.example.retrofitroom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitroom.domain.interactor.usecase.GetMovieUseCase

//class MovieViewModelProviderFactory(val moviesRepository: MovieRepositoryImpl) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return MovieViewModel(moviesRepository) as T
//    }
//}

class MovieViewModelProviderFactory(val getMovieFromuseCase: GetMovieUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(getMovieFromuseCase) as T
    }
}
