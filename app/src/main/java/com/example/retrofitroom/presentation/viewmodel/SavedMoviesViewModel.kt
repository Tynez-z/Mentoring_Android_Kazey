package com.example.retrofitroom.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitroom.common.ERROR
import com.example.retrofitroom.domain.entity.Result
import com.example.retrofitroom.domain.interactor.usecase.DeleteSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedMoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase
) : ViewModel() {

    private val moviesNews: MutableLiveData<List<Result>> = MutableLiveData()
    private val errorStateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() = viewModelScope.launch {
        val response = getMoviesUseCase.getMovies()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue(ERROR)
            return@launch
        }
        moviesNews.postValue( response.body()!!.results)
    }

    fun saveArticle(movie: Result) = viewModelScope.launch {
        saveMoviesUseCase.saveMovie(movie)
    }

    fun getSavedMovies() = getSavedMoviesUseCase.savedMovies()

    fun deleteArticle(movie: Result) = viewModelScope.launch {
        deleteSavedMoviesUseCase.deleteMovies(movie)
    }
}