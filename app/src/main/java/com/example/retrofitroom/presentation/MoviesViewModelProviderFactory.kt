package com.example.retrofitroom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitroom.domain.interactor.usecase.DeleteSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMoviesUseCase
import javax.inject.Inject

class MoviesViewModelProviderFactory @Inject constructor(
    private val deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSavedNewsUseCase: GetSavedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(
            getMoviesUseCase,
            deleteSavedMoviesUseCase,
            getSavedNewsUseCase,
            saveMoviesUseCase) as T
    }
}